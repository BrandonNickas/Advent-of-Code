import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            File input = new File("src/input");
            Scanner scanner = new Scanner(input);

            String line;
            char current;
            int diff;
            int max = 0;
            int[][] steats = new int[128][8];
            boolean[] taken = new boolean[979];

            int lowerRow = 0, higherRow = 127, lowerCol = 0, higherCol = 7, row = 0, col = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    current = line.charAt(i);

                    switch (current) {
                        case 'F':
                            diff = higherRow - lowerRow;
                            diff = diff / 2;
                            diff++;
                            higherRow = higherRow - diff;
                            break;
                        case 'B':
                            diff = higherRow - lowerRow;
                            diff = diff / 2;
                            diff++;
                            lowerRow = lowerRow + diff;
                            break;
                        case 'L':
                            diff = higherCol - lowerCol;
                            diff = diff / 2;
                            diff++;
                            higherCol = higherCol - diff;
                            break;
                        case 'R':
                            diff = higherCol - lowerCol;
                            diff = diff / 2;
                            diff++;
                            lowerCol = lowerCol + diff;
                            break;
                    }
                }

                row = lowerRow;
                col = lowerCol;
                steats[row][col] = row * 8 + col;

                if (row * 8 + col > max) {
                    max = row * 8 + col;
                }
                    lowerRow = 0; 
                    higherRow = 127;
                    lowerCol = 0; 
                    higherCol = 7;
            }

            System.out.println(max);
            
            for (int i = 0; i < 128; i++) {
                for (int j = 0; j < 8; j++) {
                    if (steats[i][j] > 0) {
                        taken[steats[i][j]] = true;
                    }
                }
            }
            for (int i = 1; i < 978; i++) {
                if (!taken[i] && taken[i-1] && taken[i+1]) {
                    System.out.println(i);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}