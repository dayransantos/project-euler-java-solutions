package tasks;

import static utils.MyMath.getPrimesBetween;

//Answer : 271176528227932684
//@see: http://www.math.niu.edu/~rusin/known-math/93_back/2squares
public class Task_233_2 implements ITask {

    final long LIM = 100000000000L;

    int cnt1 = 0;
    int cnt3 = 0;
    long pt1[] = new long[170000];
    long pt3[] = new long[170000];

    boolean onlyp3mults[] = new boolean[300000];
    long p3multSums[] = new long[300000];

    public void solving() {
        for (long p : getPrimesBetween(0, 4733728)) {
            if (p%4==1) {
                pt1[cnt1++] = p;
            } else {
                pt3[cnt3++] = p;
            }
        }
        System.out.println("4k+1 primes count: " + cnt1);
        System.out.println("4k+3 primes count: " + cnt3);

        fillP3Numbers(1, 0);
        for (int i = 1; i < p3multSums.length; ++i) {
            p3multSums[i] = p3multSums[i-1] + (onlyp3mults[i] ? i : 0);
        }


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

                    long n3 = n2 * p3;
                    if (n3 > LIM) break;

                    //n3 itself
//                    res += n3;

                    if (res < 0) {
                        System.out.println("ouch!");
                    }
                    res += p3multSums[(int) (LIM/n3)]*n3;

//                    long n4 = 3*n3;
//                    for (int m = 3; n4 <= LIM; m += 2) {
//                        if (isNotDivisible(m, pt1)) {
//                            res += n4;
//                        }
//                        n4 += 2*n3;
//                    }
                }
            }
        }

        System.out.println(res);
    }

    private boolean fillP3Numbers(long n, int ind) {
        if (n >= onlyp3mults.length) return false;

        onlyp3mults[(int)n] = true;

        for (int i = ind; i < cnt3; ++i) {
            if (!fillP3Numbers(n * pt3[i], i)) {
                break;
            }
        }

        return true;
    }

    private boolean isNotDivisible(int n, long[] pt1) {
        for (long p : pt1) {
            if (p*p > n) return true;

            if (n%p == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Tester.test(new Task_233_2());
    }
}
