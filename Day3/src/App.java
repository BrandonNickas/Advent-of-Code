import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            File input = new File("src/Input");
            Scanner reader = new Scanner(input);

            int[] trees = new int[5];
            Arrays.fill(trees,0);
            int x = 0;
            String line;
            int trees4Row = 0;

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (line.charAt(x % line.length()) == '#') {
                    trees[0]++;
                }
                if (line.charAt(x * 3 % line.length()) == '#') {
                    trees[1]++;
                }
                  
                if (line.charAt(x * 5 % line.length()) == '#') {
                    trees[2]++;
                }
              
                if (line.charAt(x * 7 % line.length()) == '#') {
                    trees[3]++;

                }
                if (x % 2 == 0 && line.charAt(trees4Row % line.length()) == '#') {
                    trees[4]++;
                    trees4Row++;
                } else if (x % 2 == 0) {
                    trees4Row++;
                }
System.out.println(x + " " + trees4Row +" " + trees[4]);
                x += 1;
                
            }

            System.out.println(trees[0] + " " + trees[1] + " " +trees[2] + " " +trees[3] + " " +trees[4]);
            System.out.println((long)trees[0] * (long)trees[1] * (long)trees[2] * (long)trees[3] * (long)trees[4]);

            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
