package tasks;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import utils.log.Logger;
import static utils.STLUtils.*;

//Answer :
public class AlZimmer2010_1_rand implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new AlZimmer2010_1_rand());
        Logger.close();
    }
    public static final int primes[] = new int[]{2, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    int bs[];
    int cs[];
    int ts[];
    int bestScore[];
    Random r = new Random();
    boolean was[];

    public void solving() {
        int last = primes[primes.length - 1];
        was = new boolean[last];
        bs = new int[last];
        cs = new int[last];
        ts = new int[last];
        bestScore = new int[last + 1];

        initBestScore();

        while (true) {
            for (int p : primes) {
                fillRand(p);
                int currScore = score(p);
                if (currScore > bestScore[p]) {
                    bestScore[p] = currScore;
                    System.arraycopy(cs, 0, bs, 0, p);

                    PrintStream out = null;
                    try {
                        out = new PrintStream(new FileOutputStream("D:\\1\\" + p + ".dat"));
                        output(System.out, p);
                        output(out, p);

                        Runtime.getRuntime().exec("cmd /c D:\\1\\!cat.bat");
                    } catch (Exception ignored) {
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
            }
        }
    }

    private void output(PrintStream out, int p) {
        out.printf("[%1$2d][%2$4d]: ", p, bestScore[p]);
        for (int i = 0; i < p; ++i) {
            out.print((i > 0 ? ", " : "") + bs[i]);
        }
        out.println(";");
    }

    private void fillRand(int p) {
        Arrays.fill(was, 0, p, false);
        for (int i = 0; i < p; ++i) {
            int n;
            do {
                n = r.nextInt(p);
            } while (was[n]);
            was[n] = true;
            cs[i] = n + 1;
        }
    }

    private int score(int p) {
        System.arraycopy(cs, 0, ts, 0, p);
        int score = 0;
        while (ts[0] != 1) {
            ++score;
            reverse(ts, 0, ts[0] - 1);
        }
        return score;
    }

    private void initBestScore() {
        for (int p : primes) {
            try {
                BufferedReader in = new BufferedReader(new FileReader("D:\\1\\" + p + ".dat"));

                Scanner sc = new Scanner(in.readLine());
                sc.useDelimiter("\\[|\\]|\\:|\\s+");
                while (!sc.hasNextInt()) {
                    sc.next();
                }
                sc.nextInt();

                while (!sc.hasNextInt()) {
                    sc.next();
                }
                bestScore[p] = sc.nextInt();

                in.close();
            } catch (IOException ex) {
            }
        }
    }
}
