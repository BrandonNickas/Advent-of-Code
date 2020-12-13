import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String busses = "";
       
        try {
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);

            busses = scanner.nextLine();

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] bus = busses.split(",");
        long finalTime = 0;
        long[] b = new long[9];
        long[] n = new long[9];
        long[] N = new long[9];
        long[] x = new long[9];
        long[] bNx = new long[9];


        int index = 0;
        long number1 = 1;
        
        b[index] = 0;
        n[index] = Integer.parseInt(bus[0]);
        number1 *= Integer.parseInt(bus[0]);
        index++;

        for (int i = 1; i < bus.length; i++) {
            if (!bus[i].equals("x")) {
                int temp = -i;
                
                while (temp < 0) {
                    temp = temp + Integer.parseInt(bus[i]);
                }
                if (temp > Integer.parseInt(bus[i])) {
                    temp = temp % Integer.parseInt(bus[i]);
                }
                
                b[index] = temp;
                n[index] = Integer.parseInt(bus[i]);
                number1 *= Integer.parseInt(bus[i]);
                index++;
            }
        }

        for (int i = 0; i < index; i++) {
            N[i] = number1 / n[i];
        }

        for (int i = 1; i < index; i++) {
            long temp = N[i];
            int times = 2;
            long number = temp;
            while (true) {
                if (temp % n[i] == 1) {
                    x[i] = times - 1;
                    break;
                }
                if (times == 1) {
                    temp = temp % n[i];
                }
                temp = number * times;
                times++;
            }
        }

        long total = 0;
        for (int i = 0; i < index; i++) {
            bNx[i] = b[i] * N[i] * x[i];
            total += bNx[i];
        }
        
        finalTime = total % number1;
        System.out.println(finalTime);
    }
}