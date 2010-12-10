import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class smallworld {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[] {"d:\\Work\\Stuff\\ProjectEuler\\facebook\\!input.txt"};
        }
        new smallworld().run(args[0]);
    }

    static class Friend {
        double x;
        double y;
        int id;

        public Friend(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    
    List<Friend> friends = new ArrayList<Friend>();
    int n;
    int closest[][];
    double dist2[][];
    private void run(String fileName) throws IOException {
        Scanner in = new Scanner(new File(fileName));

        while (in.hasNextInt()) {
            friends.add(new Friend(in.nextInt(), Double.parseDouble(in.next()), Double.parseDouble(in.next())));
        }

        n = friends.size();
        closest = new int[n][3];
        dist2 = new double[n][3];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist2[i], Double.MAX_VALUE);
        }
        
        for (int i = 0; i < n; ++i) {
            Friend f1 = friends.get(i);
            for (int j = i+1; j < n; ++j) {
                Friend f2 = friends.get(j);
                double d2 = dist2(f1.x-f2.x, f1.y-f2.y);

                putIfBetter(i, f2.id, d2);
                putIfBetter(j, f1.id, d2);
            }

            System.out.println(f1.id + " " + closest[i][0] + "," + closest[i][1] + "," + closest[i][2]);
        }
    }

    private double dist2(double dx, double dy) {
        return dx*dx + dy*dy;
    }

    private void putIfBetter(int ind1, int ind2, double d2) {
        double dist[] = dist2[ind1];
        int close[] = closest[ind1];

        int ins = -Arrays.binarySearch(dist, d2) - 1;
        if (ins <= 2) {
            for (int i = 2; i > ins; --i) {
                dist[i] = dist[i - 1];
                close[i] = close[i - 1];
            }
            dist[ins] = d2;
            close[ins] = ind2;
        }
    }
}
