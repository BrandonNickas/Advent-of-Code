import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part2 {
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
            colors[i][1] = temp [4] + " " + temp[5] + temp[6];
            if (temp.length > 9) {
                colors[i][2] = temp [8] + " " + temp[9] + temp[10];
            }
            if (temp.length > 13) {
                colors[i][3] = temp [12] + " " + temp[13] + temp[14];
            }
            if(temp.length > 17){
                colors[i][4] = temp [16] + " " + temp[17] + temp[18];
            }
        }
        
        int next = 0;
        for (int i = 0; i < numberOfColors; i++) {
            if ("shinygold".equals(colors[i][0])) {
                next = i;
                break;
            }
        }

        System.out.println(Contains(colors[next][0], "potato"));
    }

    public static int Contains(String query, String previous) {
        if (query.equals("") || query.equals(" otherbags.")) {
            return 0;
        }
 
        int next = 0;
        for (int i = 0; i < numberOfColors; i++) {
            if (query.equals(colors[i][0])) {
                next = i;
                break;
            }
        }
        
        String next1 = "", next2 = "", next3 = "", next4 = "";
        int current1 = 0, current2 = 0, current3 = 0, current4 = 0;

        next1 = colors[next][1].substring(2);
        if (!next1.equals(" otherbags.")) {
            current1 = Integer.parseInt(colors[next][1].substring(0, 1));
        }
        if (colors[next][2] != null) {
            next2 = colors[next][2].substring(2);
            current2 = Integer.parseInt(colors[next][2].substring(0, 1));
        }
        if (colors[next][3] != null) {
            next3 = colors[next][3].substring(2);
            current3 = Integer.parseInt(colors[next][3].substring(0, 1));
        }
        if (colors[next][4] != null) {
            next4 = colors[next][4].substring(2);
            current4 = Integer.parseInt(colors[next][4].substring(0, 1));
        }
        return current1 + Contains(next1, query)*current1 + current2 + Contains(next2, query)*current2 + current3 + Contains(next3, query)*current3 + current4 + Contains(next4, query)*current4;
    }
}

