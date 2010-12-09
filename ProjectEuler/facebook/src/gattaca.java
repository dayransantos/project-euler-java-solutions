import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class gattaca {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[] {"d:\\input.txt"};
        }
        new gattaca().run(args[0]);
    }

    private void run(String fileName) throws IOException {
        Scanner in = new Scanner(new File(fileName));
        int n = in.nextInt();

        StringBuffer s = new StringBuffer(n);
        for (int i = 0; i < (n%80==0?n/80:n/80+1); ++i) {
            s.append(in.next());
        }

        int k = in.nextInt();
        int beg[] = new int[k];
        int end[] = new int[k];
        int score[] = new int[k];
        for (int i = 0; i < k; ++i) {
            beg[i] = in.nextInt() + 1;
            end[i] = in.nextInt() + 1;
            score[i] = in.nextInt();
        }

        int best[] = new int[n + 1];
        best[0] = 0;
        for (int i = 1; i <= n; ++i) {
            best[i] = best[i - 1];

            for (int j = 0; j < k; ++j) {
                if (end[j] == i) {
                    best[i] = Math.max(best[i], best[beg[j] - 1] + score[j]);
                }
            }
        }

        System.out.println(best[n]);
    }
}
