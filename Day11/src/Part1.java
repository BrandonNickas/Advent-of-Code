import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
    public static int numberOfRows = 97;
    public static void main(String[] args) throws Exception {
        
        String[] lines = new String[numberOfRows];
        
        try{
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);
            
            for (int i = 0; scanner.hasNextLine(); i++) {
                lines[i] = scanner.nextLine();
            }
            scanner.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        char[][] seating = new char[numberOfRows][lines[0].length()];
        char[][] reference = new char[numberOfRows][lines[0].length()];

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < lines[0].length(); col++) {
                seating[row][col] = lines[row].charAt(col);
            }
        }
        
        Boolean change = false;
        while (!change) {
            
            for (int row = 0; row < numberOfRows; row++) {
                for (int col = 0; col < lines[0].length(); col++) {
                    reference[row][col] = seating[row][col];
                }
            }

            change = true;
            for (int row = 0; row < numberOfRows; row++) {
                for (int col = 0; col < lines[0].length(); col++) {
                    int around = around(row, col, reference, lines[0].length());
                    if (seating[row][col] == 'L' && around == 0) { //The seat is empty
                        change = false;
                        seating[row][col] = '#';
                    }
                    if (seating[row][col] == '#' && around >= 4) {
                        change = false;
                        seating[row][col] = 'L';
                    }
                }
            }
        }

        int count = 0;
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < lines[0].length(); col++) {
                if (seating[row][col] == '#') {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
    
    public static int around(int row, int col, char[][] reference, int length) {
        int around = 0;
        Boolean left = true, right = true, up = true, down = true;
        if (row - 1 < 0) {
            up = false;
        }
        if (row + 1 > numberOfRows - 1) {
            down = false;
        }
        if (col - 1 < 0) {
            left = false;
        }
        if (col + 1 > length - 1) {
            right = false;
        }

        //Top row
        if (left && up && reference[row - 1][col - 1] == '#') {
            around++;
        }
        if (up && reference[row - 1][col] == '#') {
            around++;
        }
        if (right && up && reference[row - 1][col + 1] == '#') {
            around++;
        }

        //Middle row
        if (left && reference[row][col - 1] == '#') {
            around++;
        }
        if (right && reference[row][col + 1] == '#') {
            around++;
        }

        //Bottom row
        if (left && down && reference[row + 1][col - 1] == '#') {
            around++;
        }
        if (down && reference[row + 1][col] == '#') {
            around++;
        }
        if (right && down && reference[row + 1][col + 1] == '#') {
            around++;
        }

        return around;
    }
}
