package tasks;

import static java.lang.Math.sqrt;

//Answer :
public class Task_195 implements ITask {
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
    double sq3 = sqrt(3);

    public void solving() {
        long res = 0;
        for (long a = 2; ; ++a) {
            long a2 = a * a;

            long maxb = (a2<=12*n2) ? (a-1) : (long) ((12 * n2 - 4 * n * a * sq3) / (4 * n * sq3 - 3 * a));
//            long minc2 = 3*a2/4;
//            long maxc2minus = a2 + 1 - a;
//            long maxc2plus = a2 + maxb*maxb - a*maxb;
            for (long b = 1; b <= maxb; ++b) {
                long b2 = b * b;
                long c2 = a2 + b2 - a * b;
                double dc = sqrt(c2);

                long c = (long) dc;
                if (c == a || c == b || c * c != c2) {
                    continue;
                }

//                double p = (a + b + dc);
//                if (sq3 * a * b > 2 * n * p) {
//                    break;
//                }
//                double r = sq3 * a * b / (2 * p);
//                ++res;
//                System.out.println(res + ": " + a + " " + b + " " + c + " " + r);

                ++res;
                System.out.println(res + ": " + a + " " + b + " " + c);
                if (res % 1000 == 0 || res>=LIM-10 || res < 11) {
//                    System.out.println(res + ": " + a + " " + b + " " + c);
                }
            }
        }

//        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_195());
    }
}
