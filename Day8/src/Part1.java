import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
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
        
        int index = 0, acc = 0;
        String[] temp = new String[2];
        Boolean[] completed = new Boolean[numberOfLines];
        Arrays.fill(completed, false);
        
        while (index < lines.length) {
            int flex = 0;

            if (completed[index]) {
                System.out.println(acc);
                break;
            }

            temp = lines[index].split(" ");

            if(temp[0].equals("jmp")){
                flex = Integer.parseInt(temp[1]);
                completed[index] = true;
                index += flex;
            } else if(temp[0].equals("acc")){
                flex = Integer.parseInt(temp[1]);
                acc += flex;
                completed[index] = true;
                index++;
            } else {
                completed[index] = true;
                index++;
            }
        }

    }
}
