import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int lower;
        int higher;
        int valid = 0;

        String key;
        String password;
        String numbers;

        boolean twoDigit;
        boolean lowerSpot = false;
        boolean higherSpot = false;

        try {  
            File input = new File("src/Input");
            Scanner reader = new Scanner(input);
            while (reader.hasNext()) {
                numbers = reader.next();
                if (numbers.charAt(2) == '-') {
                    lower = Integer.parseInt(numbers.substring(0,2));
                    twoDigit = true;
                } else {
                    lower = Integer.parseInt(numbers.substring(0, 1));
                    twoDigit = false;
                }
                
                if (twoDigit) {
                    higher = Integer.parseInt(numbers.substring(3));
                } else {
                    higher = Integer.parseInt(numbers.substring(2));
                }

                key = reader.next().substring(0,1);

                password = reader.next();
                
                
                if (lower - 1 < password.length() && password.substring(lower - 1, lower).equals(key)) {
                    lowerSpot = true;

                }

                if (higher - 1 < password.length() && password.substring(higher - 1, higher).equals(key)) {
                    higherSpot = true;
                    
                }

                if (lowerSpot != higherSpot) {
                    valid++;
                }

                lowerSpot = false;
                higherSpot = false;
                
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(valid);

    }
}
