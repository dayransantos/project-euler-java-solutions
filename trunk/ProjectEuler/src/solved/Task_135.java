package solved;

import tasks.*;
import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import utils.pairs.LongPair;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import static utils.FileUtils.*;

//Answer : 4989
public class Task_135 implements ITask {

    TreeSet<LongPair> all = new TreeSet<LongPair>();

    public void solving() {
        System.out.println(L(27));
        System.out.println(L(1155));
        int res = 0;
        for (int i = 1155; i < 1000000; ++i) {
            if (L(i) == 10) {
                ++res;
            }
        }

        System.out.println(res);
    }

    private int L(long t) {
        all.clear();

        int L = 0;
        for (long d1 : getDivisors(t)) {
            long d2 = t / d1;
            if (d2 < d1) {
                break;
            }

            if ((d1 + d2) % 4 == 0 && (d2 - d1) % 2 == 0) {
                long d = (d1 + d2) / 4;
                long A = (d2 - d1) / 2;


                L += addPair(d, d + A);
                L += addPair(d, d - A);

                if (L > 10) {
                    return L;
                }
            }
        }
        return L;
    }

    private int addPair(long d, long x) {
        if (x <= 0) {
            return 0;
        }

        LongPair p = new LongPair(d, x);
        if (all.contains(p)) {
            return 0;
        }

        all.add(p);

        return 1;
    }

    public static void main(String[] args) {
        Tester.test(new Task_135());
    }
}
