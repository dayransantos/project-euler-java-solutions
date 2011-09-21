package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.ZERO;
import static utils.MyMath.bi;

//Answer :
//see: http://oeis.org/A060839 for some thoughts
public class Task_272_2 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_272_2());
        Logger.close();
    }

//    long LIM = 100000000000L;
    long LIM = 30000000;

    long[] needprimes;
    int np;
    long[] otherprimes;
    int op;

    long fr[] = new long[60000];

    public void solving() {
        long hiprime = LIM / (7 * 13 * 19 * 31);
        needprimes = MyMath.getPrimesBetween(0, hiprime);
        otherprimes = new long[needprimes.length];

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

        System.out.println(np);

        BigInteger res = ZERO;
        for (int i = 0; i < needprimes.length; ++i) {
            System.out.println(i);
            res = res.add(find(i, 0, needprimes[i]));
        }
        System.out.println(res);
    }

    private BigInteger find(int ind, int cnt, long n) {
        long p = needprimes[ind];
        BigInteger res = BigInteger.ZERO;
        for (long nn = n; nn <= LIM; nn*=p) {
            if (cnt == 4) {
                res = res.add(bi(nn).multiply(bi(fr[((int) (LIM / nn))])));
            } else {
                for (int nind = ind+1; np-nind+cnt+1 >= 5 && nn*needprimes[nind] <= LIM; ++nind) {
                    res = res.add(find(nind, cnt + 1, nn*needprimes[nind]));
                }
            }
        }

        return res;
    }
}
