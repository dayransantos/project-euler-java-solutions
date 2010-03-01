package tasks;

import java.util.*;
import java.math.BigInteger;
import static utils.MyMath.*;

import utils.LongFraction;
import utils.Fraction;

//Answer :
public class Task_152_3 implements ITask {
    private static final int MAX_NUM = 80;
    private static final Fraction half = new Fraction(1, 2);

    Fraction sq[] = new Fraction[MAX_NUM+1];

    Fraction sm[] = new Fraction[MAX_NUM+2];
    Fraction halfSubSm[] = new Fraction[MAX_NUM+2];

    private static int res = 0;
    public void solving() {
        for (int i = 2; i <= MAX_NUM; ++i) {
            sq[i] = new Fraction(1, i*i);
        }

        sm[MAX_NUM+1] = Fraction.ZERO;
        halfSubSm[MAX_NUM+1] = half;
        for (int i = MAX_NUM; i >= 2; --i) {
            sm[i] = sm[i+1].add(sq[i]);
            halfSubSm[i] = half.subtract(sm[i]);
        }

        find(0, 2, Fraction.ZERO);
        System.out.println(res);
    }

    private int path[] = new int[MAX_NUM+1];
    private void find(int ind, int beg, Fraction sum) {
        if (sum.equals(half)) {
            res++;
            for (int i = 0; i < ind; ++i) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = beg; i <= MAX_NUM; ++i) {
            Fraction s2 = sum.add(sq[i]);
            if (s2.compareTo(half) > 0) {
                continue;
            }

            if (halfSubSm[i+1].compareTo(s2) > 0) {
                break;
            }

            path[ind] = i;
            find(ind+1, i+1, s2);
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_152_3());
    }
}