package tasks;

import static java.lang.Math.sqrt;
import static utils.MyMath.getCachedPrimes;

//Answer :
public class Task_195_2 implements ITask {
//       T(100) = 1234, T(1000) = 22767, and T(10000) = 359912.
//       Find T(1053779).
//    private static final long LIM = 75085391;
//    private static final long n = 1053779;

//    private static final long LIM = 359912;
//    private static final long n = 10000;
//
    private static final int LIM = 1234;
    private static final int n = 100;

//    private static final int LIM = 22767;
//    private static final int n = 1000;

    private static final long n2 = n * n;
    private static final long n212 = 12 * n2;
    private static final double sq3 = sqrt(3);

    long prs[];

    long factors[] = new long[200];
    int factPow[] = new int[200];
    int fcnt = 0;

    long res = 0;
    private long a;
    private long maxb;

    public void solving() {
//        c2 = a2 + b2 - ab;
//        b2 - b * a - (c2 - a2) = 0;
//        D = a2 + 4c2 - 4a2 = 4c2 - 3a2 = B2;
//
//        b = (a +- sqrt(4c2 - 3a2))/2
//
//        B2 + 3a2 = (2c)^2
//
//        3a2 = (2c - B)(2c + B) => B, c => b1,2 = a +- sqrt(B)

        prs = getCachedPrimes();

        System.out.println("");

        for (a = 2; ; ++a) {
            long ores = res;
            long a2 = a * a;
            maxb = (a2<=12*n2) ? (a-1) : (long) ((12 * n2 - 4 * n * a * sq3) / (4 * n * sq3 - 3 * a));

            factorize3a2(a);

            find(0, 1, 3 * a2);

            long dr = res - ores;
            if (dr > 7) {
                System.out.println(a);
            }
        }
//        System.out.println(res);
    }

    private void find(int ind, long f1, long f2) {
        if (f1 > f2) {
            return;
        }

        if (ind == fcnt) {
            long fs = f1 + f2;
            long fd = f2 - f1;
            if (fs % 4 != 0 && fd%2!=0) return;

            long c = fs / 4;
            long B = fd / 2;
            long b1 = a + B;
            long b2 = a - B;

            check(b1);
            check(b2);

//        System.out.println(res + ": " + a + " " + b + " " + c);

            return;
        }

        long factor = factors[ind];
        long maxcnt = factPow[ind];
        if (factor == 2) {
            maxcnt--;
        }
        for (int cnt = factor==2?1:0; cnt <= maxcnt; cnt++, f1 *= factor, f2 /= factor) {
            find(ind + 1, f1, f2);
        }
    }

    private boolean check(long b2) {
        if (b2%2!=0 || b2/2 > maxb || b2 < 1) {
            return false;
        }
        ++res;

        return true;
    }

    private void factorize3a2(long a) {
        fcnt = 0;
        for (long p : prs) {
            if (p * p > a && p != 3) {
                break;
            }

            int cnt = 0;
            while (a % p == 0) {
                cnt += 2;
                a /= p;
            }

            if (p == 3) {
                ++cnt;
            }

            if (cnt > 0) {
                factors[fcnt] = p;
                factPow[fcnt++] = cnt;
            }
        }

        if (a != 1) {
            factors[fcnt] = a;
            factPow[fcnt++] = a==3 ? 3 : 2;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_195_2());
    }
}
