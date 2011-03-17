package tasks;

import utils.log.Logger;

import java.util.Arrays;
import java.util.Random;

import static utils.STLUtils.next_permutation;
import static utils.STLUtils.reverse;

//Answer :
public class AlZimmer2010_2_perms implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new AlZimmer2010_2_perms());
        Logger.close();
    }

//    public static final int primes[] = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
    public static final int primes[] = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

    int bs[];
    int cs[];
    int ts[];

    Random r = new Random();

    boolean was[];

    public void solving() {
        int last = primes[primes.length - 1];
        was = new boolean[last];
        bs = new int[last];
        cs = new int[last];
        ts = new int[last];
        
//        cs = new int[] {2, 9, 4, 5, 11, 12, 10, 1, 8, 13, 3, 6, 7};
//        testOut(cs, 13);

        int steps = 2000000;
        boolean first = true;
        for (int p : primes) {
            int bestScore = -1;
            
            for (int i = 0; i < p; ++i) {
                cs[i] = i+1;
            }

            do {
                int currScore = score(p);
                if (currScore > bestScore) {
                    bestScore = currScore;
                    System.arraycopy(cs, 0, bs, 0, p);
                }
            } while (next_permutation(cs, p));

            if (!first) {
                System.out.println(";");
            } else {
                first = false;
            }

            System.out.printf("[%1$2d][%2$4d]: ", p, bestScore);
            for (int i = 0; i < p; ++i) {
                System.out.print((i > 0? ", " : "") + bs[i]);
            }
        }
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
            reverse(ts, 0, ts[0]-1);
        }
        return score;
    }

    private void testOut(int[] cs, int p) {
        System.arraycopy(cs, 0, ts, 0, p);
        int score = 0;
        while (ts[0] != 1) {
            ++score;
            reverse(ts, 0, ts[0]-1);

            for (int i = 0; i < p; ++i) {
                System.out.print((i > 0? ", " : "") + ts[i]);
            }
            System.out.println("");
        }
            for (int i = 0; i < p; ++i) {
                System.out.print((i > 0? ", " : "") + ts[i]);
            }
            System.out.println("");
    }
}
