import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static int nunmbersOfLines = 92;
    public static long[] option = new long[nunmbersOfLines + 1];
    public static int[] lines = new int[nunmbersOfLines + 1];
    public static void main(String[] args) throws Exception {
        int max = 0;
        try {
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);

            for (int i = 0; scanner.hasNextLine(); i++) {
                lines[i] = Integer.parseInt(scanner.nextLine());
                if (lines[i] > max) {
                    max = lines[i];
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lines[nunmbersOfLines] = max + 3;
        Arrays.sort(lines);
        Arrays.fill(option, 0);
        
        option[0] = 1;

        System.out.println(Possible(nunmbersOfLines));
        System.out.println("Hi");
    }
    
    public static long Possible(int postion) {
        int mod = 0;
        if (lines[postion] - 3 <= 0) {
            mod = 1;
        }
       
        if (postion - 3 >= 0 && option[postion - 1] != 0 && lines[postion - 3] >= lines[postion] - 3) {
            option[postion] = option[postion - 3] + option[postion - 2] + option[postion - 1] + mod;
            return option[postion];

        } else if (postion - 2 >= 0 && option[postion - 1] != 0 && lines[postion - 2] >= lines[postion] - 3) {
            option[postion] = option[postion - 2] + option[postion - 1] + mod;
            return option[postion];

        } else if (postion - 1 >= 0 && option[postion - 1] != 0) {
            option[postion] = option[postion - 1] + mod;
            return option[postion];
        }

        if (postion - 3 >= 0 && lines[postion - 3] >= lines[postion] - 3) {
            return Possible(postion - 1) + Possible(postion - 2) + Possible(postion - 3) + mod;
        } else if (postion - 2 >= 0 && lines[postion - 2] >= lines[postion] - 3 ) {
            return Possible(postion - 1) + Possible(postion - 2) + mod;
        } else if (postion - 1 >= 0) {
            return Possible(postion - 1) + mod;
        }

        return 1;
    }
}