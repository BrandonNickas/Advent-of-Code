import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part2 {
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
                    if (seating[row][col] == '#' && around >= 5) {
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
        Boolean done1 = true, done2 = true, done3 = true, done4 = true, done5 = true, done6 = true, done7 = true, done8 = true;
        for (int ring = 1; ring < 100; ring++) {
            Boolean left = true, right = true, up = true, down = true;
            
            if (row - ring < 0) {
                up = false;
            }
            if (row + ring > numberOfRows - 1) {
                down = false;
            }
            if (col - ring < 0) {
                left = false;
            }
            if (col + ring > length - 1) {
                right = false;
            }

            //Top row
            if (done1 && left && up && reference[row - ring][col - ring] == '#') {
                around++;
                done1 = false;
            } else if (done1 && left && up && reference[row - ring][col - ring] == 'L') {
                done1 = false;
            }

            if (done2 && up && reference[row - ring][col] == '#') {
                around++;
                done2 = false;
            } else if (done2 && up && reference[row - ring][col] == 'L') {
                done2 = false;
            }

            if (done3 && right && up && reference[row - ring][col + ring] == '#') {
                around++;
                done3 = false;
            } else if (done3 && right && up && reference[row - ring][col + ring] == 'L'){
                done3 = false;
            }

            //Middle row
            if (done4 && left && reference[row][col - ring] == '#') {
                around++;
                done4 = false;
            } else if (done4 && left && reference[row][col - ring] == 'L') {
                done4 = false;
            }

            if (done5 && right && reference[row][col + ring] == '#') {
                around++;
                done5 = false;
            } else if (done5 && right && reference[row][col + ring] == 'L') {
                done5 = false;
            }

            //Bottom row
            if (done6 && left && down && reference[row + ring][col - ring] == '#') {
                around++;
                done6 = false;
            } else if(done6 && left && down && reference[row + ring][col - ring] == 'L'){
                done6 = false;
            }

            if (done7 && down && reference[row + ring][col] == '#') {
                around++;
                done7 = false;
            } else if (done7 && down && reference[row + ring][col] == 'L') {
                done7 = false;
            }

            if (done8 && right && down && reference[row + ring][col + ring] == '#') {
                around++;
                done8 = false;
            } else if (done8 && right && down && reference[row + ring][col + ring] == 'L') {
                done8 = false;
            }
        }
        return around;
    }
}
