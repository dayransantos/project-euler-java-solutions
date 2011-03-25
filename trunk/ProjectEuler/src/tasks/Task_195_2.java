package tasks;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

//Answer :
public class Task_195_2 implements ITask {
//       T(100) = 1234, T(1000) = 22767, and T(10000) = 359912.
//       Find T(1053779).

//    private static final int LIM = 1234;
//    private static final int n = 100;

    private static final int LIM = 22767;
    private static final int n = 1000;

    private static final int n2 = n * n;
    double sq3 = sqrt(3);

    public void solving() {
        System.out.println("");

        long res = 0;
        for (long a = 2; ; ++a) {
            long a2 = a * a;

            long maxb = (a2<=12*n2) ? (a-1) : (long) ((12 * n2 - 4 * n * a * sq3) / (4 * n * sq3 - 3 * a));
            if (maxb < 1) break;

            long maxb2 = maxb*maxb;

            long minc2 = 3*a2/4;
            long maxc2minus = a2 + 1 - a;
            long maxc2plus = a2 + maxb2 - a*maxb;

            if (2*maxb >= a) {
                long minc = (long) ceil(sq3 * a / 2);
                long maxcminus = (long) sqrt(a2 + 1 - a);

                long maxcplus = (long) sqrt(a2 + maxb2 - a * maxb);

            } else {

            }

//            for (long b = 1; b < a; ++b) {
            for (long b = 1; b<a && (b*(4*n*sq3 - 3*a) < 4*n*(3*n-sq3)); ++b) {
                long b2 = b * b;
                long c2 = a2 + b2 - a * b;
                double dc = sqrt(c2);

                long c = (long) dc;
                if (c == a || c == b || c * c != c2) {
                    continue;
                }

                ++res;
                System.out.println(res + ": " + a + " " + b + " " + c);
//                } else {
//                    break;
//                }
            }
        }

//        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_195_2());
    }
}
