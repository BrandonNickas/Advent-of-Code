import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception {
        int numberOfLines = 1000;
        int preambleLength = 25;
        Long[] lines = new Long[numberOfLines];

        try{
            File input  = new File("src/Input");
            Scanner scanner = new Scanner(input);

            for (int i = 0; scanner.hasNextLine(); i++) {
                lines[i] = Long.parseLong(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int wantedIndex = 0;
        long wantedNumber = 0;
        for (int index = preambleLength; index < numberOfLines; index++) {
            Long wanted = lines[index];
            Boolean found = false;

            for (int i = index - preambleLength; i < index; i++) {
                for (int j = index - preambleLength; j < index; j++) {
                    if (j != i && wanted == lines[i] + lines[j]) {
                        found = true;
                        break;
                    }
                    if (found) {
                        break;
                    }
                }
            }
            if (!found) {
                wantedIndex = index;
                wantedNumber = lines[index];
                break;
            }
        }
        
        int start = 0, end = 0;
        boolean found = false;
        for (int i = 0; i < wantedIndex; i++) {
            long sum = lines[i];
            for (int j = i + 1; j < wantedIndex; j++) {
                sum = sum + lines[j];
                if (sum == wantedNumber) {
                    found = true;
                    start = i;
                    end = j;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        long max = 0, min = lines[start];
        for (int i = start; i < end; i++) {
            if (lines[i] > max) {
                max = lines[i];
            }
            if (lines[i] < min) {
                min = lines[i];
            }
        }

        System.out.print(min + max);
    }
}