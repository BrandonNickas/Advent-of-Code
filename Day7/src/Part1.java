import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
    public static int numberOfColors = 594;
    public static String[][] colors = new String[numberOfColors][5];
    public static String[] accpeted = new String[numberOfColors];
    public static int index = 0;
    public static void main(String[] args) throws Exception {
        
        String[] lines = new String[numberOfColors];
        try{
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

        

        for (int i = 0; i < lines.length; i++) {
            String[] temp = lines[i].split(" ");

            colors[i][0] = temp[0] + temp[1];
            colors[i][1] = temp[5] + temp[6];
            if (temp.length > 9) {
                colors[i][2] = temp[9] + temp[10];
            }
            if (temp.length > 13) {
                colors[i][3] = temp[13] + temp[14];
            }
            if(temp.length > 17){
                colors[i][4] = temp[17] + temp[18];
            }
        }
        
        int result = 0;

        for (int i = 0; i < numberOfColors; i++) {
            Boolean getToGold = Contains(colors[i][0], "potato");
            if (getToGold) {
                result++;
            }
        }

        System.out.println(result - 1);

    }

    public static boolean Contains(String query, String previous) {
        if (query == null || query.equals("otherbags.")) {
            return false;
        } else if (query.equals("shinygold")) {
            if (!previous.equals("potato")) {
                accpeted[index] = previous;
                index++;
            }
            return true;  
        }
        
        for (int i = 0; i < accpeted.length; i++) {
            if (accpeted[i] != null && query.equals(accpeted[i])) {
                if (!previous.equals("potato")) {
                    accpeted[index] = previous;
                    index++;
                }
                return true;
            }            
        }
 
        int next = 0;
        for (int i = 0; i < numberOfColors; i++) {
            if(query.equals(colors[i][0])){
                next = i;
                break;
            }
        }
        
        return Contains(colors[next][1], query) || Contains(colors[next][2], query) || Contains(colors[next][3], query) || Contains(colors[next][4], query);
    }
}

