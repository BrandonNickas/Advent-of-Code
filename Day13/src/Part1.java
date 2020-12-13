import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws Exception {
        int earliest = 0;
        String busses = "";
       
        try {
            File input = new File("src/Input");
            Scanner scanner = new Scanner(input);

            earliest = Integer.parseInt(scanner.nextLine());
            busses = scanner.nextLine();

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] bus = busses.split(",");
        int[] ids = new int[10];
        int index = 0;

        for (int i = 0; i < bus.length; i++) {
            if (!bus[i].equals("x")) {
                ids[index] = Integer.parseInt(bus[i]);
                index++;
            }
        }

        int min = 99999999;
        int soonest = 0;

        for (int i = 0; i < index; i++) {
            int current = ids[i];

            while (current < earliest) {
                current = current + ids[i];
            }

            int waiting = current - earliest;

            if (waiting < min) {
                min = waiting;
                soonest = i;
            }
        }

        System.out.println(min * ids[soonest]);
    }
}
