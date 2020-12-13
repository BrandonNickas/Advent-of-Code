import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws Exception {
        int numberOfLines = 5;
        String[] lines = new String[numberOfLines];

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

        String[] directions = { "east", "south", "west", "north" };
        int hor = 0, ver = 0, currentDirection = 0;

        for (int i = 0; i < lines.length; i++) {
            int number = Integer.parseInt(lines[i].substring(1));
            switch (lines[i].charAt(0)) {
                case 'N':
                    ver += number;
                    break;
                case 'S':
                    ver -= number;
                    break;
                case 'E':
                    hor += number;
                    break;
                case 'W':
                    hor -= number;
                    break;
                case 'F':
                    if (currentDirection == 0) {
                        hor += number;
                    } else if (currentDirection == 1) {
                        ver -= number;
                    } else if (currentDirection == 2) {
                        hor -= number;
                    } else {
                        ver += number;
                    }
                    break;
                case 'R':
                    number = number / 90;
                    currentDirection = currentDirection + number; 
                    currentDirection = currentDirection % 4;
                    break;
                case 'L':
                    number = number / 90;
                    currentDirection = currentDirection - number;
                    while (currentDirection < 0) {
                        currentDirection = currentDirection + 4;
                    }
                    if (currentDirection > 4) {
                        currentDirection = currentDirection % 4; 
                    }
                    break;
            }
        }
        int totalDistance = Math.abs(hor) + Math.abs(ver);
        System.out.println(totalDistance);
    }
}
