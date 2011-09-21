package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.util.Arrays;

//Answer :
//see: http://oeis.org/A060839 for some thoughts
public class Task_272 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_272());
        Logger.close();
    }

//    long LIM = 100000000000L;
    long LIM = 20000000;

    long[] needprimes;
    int np;
    long[] otherprimes;
    int op;

    long fr[] = new long[60000];

    public void solving() {
        long hiprime = LIM / (7 * 13 * 19 * 31);
        needprimes = MyMath.getPrimesBetween(0, hiprime);
        otherprimes = new long[needprimes.length];

        Arrays.fill(fr, -1);

        fr[0] = fr[1] = 1;
        for (int i = 2; i < fr.length; ++i) {
            boolean fit = true;
            for (long p : MyMath.getPrimeDivisors(i)) {
                if (p%3 == 1) {
                    fit = false;
                    break;
                }
            }
            fr[i] = fr[i-1] + (fit ? i : 0);
        }

        np = 0;
        op = 0;
        for (long p : needprimes) {
            if (p%3 == 1) {
                needprimes[np++] = p;
            } else {
                otherprimes[op++] = p;
            }
        }

        long res = 0;
        for (int i = 0; i < needprimes.length; ++i) {
            System.out.println(i);
            res += find(i, 0, 1);
        }
        System.out.println(res);
    }

    private long find(int ind, int cnt, long n) {
        long p = needprimes[ind];
        long res = 0;
        for (long nn = n*p; nn <= LIM; nn*=p) {
            if (cnt == 4) {
                res += nn * fr[((int) (LIM / nn))];
            } else {
                for (int nind = ind+1; np-nind+cnt+1 >= 5; ++nind) {
                    res += find(nind, cnt + 1, nn);
                }
            }
        }

        return res;
    }
}
