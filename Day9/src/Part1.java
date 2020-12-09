import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
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
        
        for(int index = preambleLength; index < numberOfLines; index++){
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
                System.out.println(lines[index]);
                break;
            }
        }
    }
}
