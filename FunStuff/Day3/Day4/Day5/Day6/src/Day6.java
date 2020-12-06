import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws Exception {
        String[] lines = new String[2177]; 
        try {
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);

            int i = 0;
            while (scanner.hasNextLine()) {
                lines[i] = scanner.nextLine();
                i++;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean[][] yes = new boolean[8][26];
        int yesAmt = 0;
        int person = 0;
        Boolean allAgree = true;

        for (int i = 0; i < lines.length; i++) {
            if (i == lines.length - 1 || lines[i].length() == 0) {
                for (int j = 0; j < 26; j++) {
                    allAgree = true;
                    for (int k = 0; k < person; k++) {
                        allAgree = allAgree && yes[k][j];
                            if (allAgree && k == person - 1) {
                                yesAmt++;
                            }
                    }
                }
                person = 0;
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 26; k++) {
                        yes[j][k] = false;
                    }
                }
            } else {
                for (int j = 0; j < lines[i].length(); j++) {
                    int letter = lines[i].charAt(j) - 97;
                    yes[person][letter] = true;
                }
                person++;
            }
        }
        System.out.println(yesAmt);
    }
}