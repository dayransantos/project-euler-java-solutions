package tasks;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;

//Answer :
//@see: http://www.math.niu.edu/~rusin/known-math/93_back/2squares
public class Task_233 implements ITask {

    final long LIM = 100000000000L;

    long prs[];

    int factCnt;
    long factors[] = new long[200];
    int factPow[] = new int[200];

    public void solving() {
        prs = getCachedPrimes();

        System.out.println(f(1));
        System.out.println(f(2));
        System.out.println(f(3));
        System.out.println(f(4));
        System.out.println(f(5));
        System.out.println(f(10000));

        long res = 0;

        for (int i = 2; i <= LIM; ++i) {
            if (i%1000000 == 0) {
                System.out.println("Progress: " + i);
            }

            if (f(i) == 420) {
                res += i;
            }
        }

        System.out.println(res);
    }

    private long f(long n) {
        long res = 1;

        for (long p : prs) {
            if (p * p > n) {
                break;
            }

            int cnt = 0;
            while (n % p == 0) {
                cnt += 2;
                n /= p;
            }

            if (p%4==1) {
                res *= (cnt+1);
                if (res > 105) return 421;
            }
        }

        if (n != 1 && n%4==1) {
            res *= (2+1);
        }

        return 4*res;

//        res = (res+1)/2;

//        return (res-1)*8 + 4;
    }

    public static void main(String[] args) {
        Tester.test(new Task_233());
    }

    private boolean isNotDivisible(int n, long[] pt1) {
        for (long p : pt1) {
            if (p*p > n) return true;

            if (n%p == 0) return false;
        }
        return true;
    }
}
