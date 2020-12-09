import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception {
        int numberOfLines = 626;
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
        
        String[] temp = new String[2];
        Boolean[] completed = new Boolean[numberOfLines];
        Arrays.fill(completed, false);
        
        int attempt = 0;
        String[] attempString = new String[2];
        int flex = 0;

        while (attempt < lines.length) {
            boolean wasJmp = false, wasNop = false;
            attempString = lines[attempt].split(" ");
            
            if (attempString[0].equals("jmp")) {
                lines[attempt] = "nop " + attempString[1];
                wasJmp = true;
            } else if (attempString[0].equals("nop")){
                lines[attempt] = "jmp " + attempString[1];
                wasNop = true;
            }

            if (!attempString[0].equals("acc")) {
                int index = 0, acc = 0;
                Arrays.fill(completed, false);
                while (index < lines.length + 1) {

                    if (index == lines.length) {
                        System.out.println(acc);
                        System.exit(0);
                    }

                    if (completed[index]) {
                        break;
                    }

                    temp = lines[index].split(" ");

                    if (temp[0].equals("jmp")) {
                        flex = Integer.parseInt(temp[1]);
                        completed[index] = true;
                        index += flex;
                    } else if (temp[0].equals("acc")) {
                        flex = Integer.parseInt(temp[1]);
                        acc += flex;
                        index++;
                    } else {
                        index++;
                    }

                }
            }

            if(wasJmp){
                lines[attempt] = "jmp " + attempString[1];
            } else if (wasNop) {
                lines[attempt] = "nop " + attempString[1];
            }
            attempt++;
        }

    }
}