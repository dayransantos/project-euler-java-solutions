package tasks;

import static java.lang.Math.sqrt;
import static utils.MyMath.*;

//Answer :
//@see: http://pythag.net/node10.html
public class Task_195_3 implements ITask {
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
            long a2 = a * a;
            maxb = (a2 <= 12 * n2) ? (a - 1) : (long) ((12 * n2 - 4 * n * a * sq3) / (4 * n * sq3 - 3 * a));
//            maxb = (long) ((12 * n2 - 4 * n * a * sq3) / (4 * n * sq3 - 3 * a));

            factorize3a2(a);

            find(0, 1, 3 * a2);
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
            if (fs % 4 != 0 && fd % 2 != 0) {
                return;
            }

            long c = fs / 4;
            long B = fd / 2;
            long b1 = a + B;
            long b2 = a - B;

            if (gcd(a, c) != 1) {
                return;
            }

            if (check(a, b1, c)) {
                System.out.println(res + ": " + a + " " + b1 / 2 + " " + c);
            }
            if (check(a, b2, c)) {
                System.out.println(res + ": " + a + " " + b2 / 2 + " " + c);
            }

            return;
        }

        long factor = factors[ind];
        int maxcnt = factPow[ind];
        int mincnt = 0;
        if (factor == 2) {
            mincnt++;
            maxcnt--;
            f1 *= 2;
            f2 /= 2;
        }
        if (factor > 3) {
            long fp = pow(factor, maxcnt);
            //give all to the only factor, otherwise a, b and c will not be co-prime
            find(ind + 1, f1 * fp, f2 / fp);
            find(ind + 1, f1, f2);
        } else {
            for (int cnt = mincnt; cnt <= maxcnt; cnt++, f1 *= factor, f2 /= factor) {
                find(ind + 1, f1, f2);
            }
        }
    }

    private boolean check(long a, long b2, long c) {
        if (b2 % 2 != 0) {
            return false;
        }
        long b = b2 / 2;
        if (b > maxb || b < 1 || gcd(a, b) != 1 || gcd(c, b) != 1) {
            return false;
        }

//        long maxa = 12 * n2;
//        res += maxa / a;

        res += maxb / b;

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
            factPow[fcnt++] = a == 3 ? 3 : 2;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_195_3());
    }
}
