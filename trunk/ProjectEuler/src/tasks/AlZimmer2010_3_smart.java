package tasks;

import utils.log.Logger;

import java.util.Random;

import static utils.STLUtils.reverse;

//Answer :
public class AlZimmer2010_3_smart implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new AlZimmer2010_3_smart());
        Logger.close();
    }

    public static final int primes[] = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    int bs[];
    int cs[];
    int ts[];
    int p;
    int bestScore;
    boolean first = true;

    Random r = new Random();

    boolean was[];

    public void solving() {
        int last = primes[primes.length - 1];
        was = new boolean[last];
        bs = new int[last];
        cs = new int[last];
        ts = new int[last];
        
        for (int pi : primes) {
            p = pi;
            
            for (int i = 0; i < p; ++i) {
                cs[i] = i+1;
            }

            findDeepest(p, 0);

            outputSeq();
        }
    }

    private void findDeepest(int p, int curr) {
        for (int i = 1; i < p; ++i) {
            if (cs[i] == i + 1) {
                reverse(cs, 0, i);
                findDeepest(p, curr+1);
                reverse(cs, 0, i);
            }
        }

        if (curr > bestScore) {
            bestScore = curr;
            System.arraycopy(cs, 0, bs, 0, p);
        }
    }

    private void outputSeq() {
        if (!first) {
            System.out.println(";");
        } else {
            first = false;
        }

        System.out.printf("[%1$2d][%2$4d]: ", p, bestScore);
        System.out.printf("");
        for (int i = 0; i < p; ++i) {
            System.out.print((i > 0? ", " : "") + bs[i]);
        }
    }
}
