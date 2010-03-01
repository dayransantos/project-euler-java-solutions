package tasks;

import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import static utils.FileUtils.*;

//Answer :
public class Task_195 implements ITask {

//    private static final int LIM = 1234;
//    private static final int n = 100;

    private static final int LIM = 22767;
    private static final int n = 1000;

    private static final int n2 = n * n;
    double sq3 = sqrt(3);

    public void solving() {

        System.out.println("");

        long res = 0;
        for (long a = 2; res < LIM; ++a) {
            long a2 = a * a;

            for (long b = 1; b < a; ++b) {
                long b2 = b * b;
                long c2 = a2 + b2 - a * b;
                double dc = sqrt(c2);

                double p = (a + b + dc);
                if (sq3 * a * b > 2 * n * p) {
                    break;
                }

                long c = (long) dc;
                if (c == a || c == b || c * c != c2) {
                    continue;
                }

//                if (3 * a2 * b2 <= 4 * n2 * p * p) {
//                if (sq3 * a * b <= 2 * n * p) {
                double r = sq3 * a * b / (2 * p);
                ++res;
                System.out.println(res + ": " + a + " " + b + " " + c + " " + r);
//                } else {
//                    break;
//                }
            }
        }

//        System.out.println(res);
    }

    public static void main(String[] args) {
//        long a = 42245;
//        long b = 42008;
//        long c = 42127;
//        long a2 = a*a;
//        long b2 = b*b;
//        long c2 = c*c;
//
//        long p = (a + b + c);
//
//        if (3L * a2 * b2 <= 4L * n2 * p * p) {
//            System.out.println("yes");
//        }

        Tester.test(new Task_195());
    }
}
