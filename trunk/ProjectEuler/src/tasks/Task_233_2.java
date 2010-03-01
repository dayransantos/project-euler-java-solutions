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
public class Task_233_2 implements ITask {

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

        int cnt1 = 0;
        int cnt3 = 0;
        long pt1[] = new long[170000];
        long pt3[] = new long[170000];
        for (long p : getPrimesBetween(0, 4733728)) {
            if (p%4==1) {
                pt1[cnt1++] = p;
            } else {
                pt3[cnt3++] = p;
            }
        }
        System.out.println("4k+1 primes count: " + cnt1);
        System.out.println("4k+3 primes count: " + cnt3);

        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            if (i%2 == 0) {
                System.out.println("Progress: " + i);
            }

            long p1 = pt1[i];
            long n = p1*p1*p1;
            if (n > LIM) break;
            for (int j = 0; j < cnt1; ++j) {
                long p2 = pt1[j];
                if (p2 == p1) continue;
                
                long n2 = n * p2*p2;
                if (n2 > LIM) break;
                for (int k = 0; k < cnt1; ++k) {
                    long p3 = pt1[k];
                    if (p3 == p1 || p3==p2) continue;

                    long n3 = n * p3;
                    if (n3 > LIM) break;

                    //n3 itself
                    res += n3;

                    long n4 = n3+n3;
                    for (int m = 2; n4 <= LIM; ++m) {
//                        if (isNotDivisible(m, pt1)) {
                            res += n4;
  //                      }
                        n4 *= 2;
//                        n4 += n3;
                    }
                }
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
        Tester.test(new Task_233_2());
    }

    private boolean isNotDivisible(int n, long[] pt1) {
        for (long p : pt1) {
            if (p*p > n) return true;

            if (n%p == 0) return false;
        }
        return true;
    }
}
