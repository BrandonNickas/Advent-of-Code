import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception {
        int numberOfLines = 787;
        String[] lines = new String[numberOfLines];

        try {
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
        int shipHor = 0, shipVer = 0, currentDirection = 0, wayHor = 10, wayVer = 1;

        for (int i = 0; i < lines.length; i++) {
            int number = Integer.parseInt(lines[i].substring(1));
            switch (lines[i].charAt(0)) {
                case 'N':
                    wayVer += number;
                    break;
                case 'S':
                    wayVer -= number;
                    break;
                case 'E':
                    wayHor += number;
                    break;
                case 'W':
                    wayHor -= number;
                    break;
                case 'F':
                    shipHor = shipHor + number * wayHor;
                    shipVer = shipVer + number * wayVer;
                    break;
                case 'R':
                    number = number / 90;
                    
                    if (wayHor > 0 && wayVer > 0) {
                        currentDirection = 0;
                    }
                    if (wayHor > 0 && wayVer < 0) {
                        currentDirection = 1;
                    }
                    if (wayHor < 0 && wayVer < 0) {
                        currentDirection = 2;
                    }
                    if (wayHor < 0 && wayVer > 0) {
                        currentDirection = 3;
                    }


                    currentDirection = currentDirection + number;
                    currentDirection = currentDirection % 4;

                    if (number % 2 == 1) {
                        int temp = wayHor;
                        wayHor = wayVer;
                        wayVer = temp;
                    }

                    wayHor = signHor(wayHor, currentDirection);
                    wayVer = signVer(wayVer, currentDirection);
                    break;
                case 'L':
                    number = number / 90;
                    
                    if (wayHor > 0 && wayVer > 0) {
                        currentDirection = 0;
                    }
                    if (wayHor > 0 && wayVer < 0) {
                        currentDirection = 1;
                    }
                    if (wayHor < 0 && wayVer < 0) {
                        currentDirection = 2;
                    }
                    if (wayHor < 0 && wayVer > 0) {
                        currentDirection = 3;
                    }

                    currentDirection = currentDirection - number;
                    
                    while (currentDirection < 0) {
                        currentDirection = currentDirection + 4;
                    }
                    if (currentDirection > 4) {
                        currentDirection = currentDirection % 4;
                    }



                    if (number % 2 == 1) {
                        int temp = wayHor;
                        wayHor = wayVer;
                        wayVer = temp;
                    }

                    wayHor = signHor(wayHor, currentDirection);
                    wayVer = signVer(wayVer, currentDirection);
                    break;
            }
            System.out.println(shipHor + " " + shipVer + " " + wayHor + " " + wayVer);
        }
        int totalDistance = Math.abs(shipHor) + Math.abs(shipVer);
        System.out.println(totalDistance);
    }
    
    public static int signHor(int wayHor, int currentDirection) {
        if (currentDirection == 0 || currentDirection == 1) {
            if (wayHor < 0) {
                wayHor *= -1;
            }
        }
        if (currentDirection == 3 || currentDirection == 2) {
            if(wayHor > 0){
                wayHor *= -1;
            }
        }
        return wayHor;
    }

    public static int signVer(int wayVer, int currentDirection) {
        if (currentDirection == 0 || currentDirection == 3) {
            if (wayVer < 0) {
                wayVer *= -1;
            }
        }
        if (currentDirection == 2 || currentDirection == 1) {
            if (wayVer > 0) {
                wayVer *= -1;
            }
        }
        return wayVer;
    }
}
