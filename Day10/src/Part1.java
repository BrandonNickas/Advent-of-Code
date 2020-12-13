import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws Exception {
        int nunmbersOfLines = 92;
        int[] lines = new int[nunmbersOfLines];

        try{
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);

            for (int i = 0; scanner.hasNextLine(); i++) {
                lines[i] = Integer.parseInt(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.sort(lines);

        int diff1 = 0, diff3 = 0;
        for (int i = 0; i < lines.length; i++) {
            if (i == 0 || i == nunmbersOfLines) {
                if (lines[0] == 1) {
                    diff1++;
                } else if (lines[0] == 3 || i == nunmbersOfLines) {
                    diff3++;
                }
            }

            if (i != 0 && lines[i] - lines[i - 1] == 1) {
                diff1++;
            } else if (i != 0 && lines[i] - lines[i - 1] == 3) {
                diff3++;
            }
        }
        
        diff3++;

        System.out.println(diff1 * diff3);
    }
}
