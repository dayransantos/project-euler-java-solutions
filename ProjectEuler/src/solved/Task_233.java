package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getPrimesBetween;

//Answer : 271204031455541309
//@see: http://www.math.niu.edu/~rusin/known-math/93_back/2squares
public class Task_233 implements ITask {

    final long LIM = 100000000000L;
    final long LIM2 = LIM / 2;

    int cnt1 = 0;
    int cnt3 = 0;
    int cnt32 = 0;
    long pt1[] = new long[200000];
    long pt3[] = new long[200000];
    long pt32[] = new long[200000];

    boolean onlyp3mults[] = new boolean[300000];
    long p3multSums[] = new long[300000];

    boolean onlyp32mults[] = new boolean[300000];
    long p32multSums[] = new long[300000];

    public void solving() {
        pt32[cnt32++] = 2;
        for (long p : getPrimesBetween(3, 4733728)) {
            if (p % 4 == 1) {
                pt1[cnt1++] = p;
            } else {
                pt3[cnt3++] = p;
                pt32[cnt32++] = p;
            }
        }

        fillSuitableNumbers(1, 0, onlyp3mults, pt3, cnt3);
        for (int i = 1; i < p3multSums.length; ++i) {
            p3multSums[i] = p3multSums[i - 1] + (onlyp3mults[i] ? i : 0);
        }

        fillSuitableNumbers(1, 0, onlyp32mults, pt32, cnt32);
        for (int i = 1; i < p32multSums.length; ++i) {
            p32multSums[i] = p32multSums[i - 1] + (onlyp32mults[i] ? i : 0);
        }

        long res = sumWith123factors(p3multSums, LIM, 1) + sumWith123factors(p32multSums, LIM2, 2) +
                   sumWith73factors(p3multSums, LIM, 1) + sumWith73factors(p32multSums, LIM2, 2) +
                   sumWith102factors(p3multSums, LIM, 1) + sumWith102factors(p32multSums, LIM2, 2);

        System.out.println(res);
    }

    private long sumWith123factors(long[] suitablesSum, long LIM, int factor) {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                for (int k = 0; k < cnt1; ++k) {
                    if (i == k || j == k) {
                        continue;
                    }

                    long p3 = pt1[k];
                    long n3 = n2 * p3;

                    if (n3 > LIM) {
                        break;
                    }

                    res += suitablesSum[(int) (LIM / n3)] * n3 * factor;
                }
            }
        }
        return res;
    }

    private long sumWith73factors(long[] suitablesSum, long LIM, int factor) {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1 * p1 * p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                res += suitablesSum[(int) (LIM / n2)] * n2 * factor;
            }
        }
        return res;
    }

    private long sumWith102factors(long[] suitablesSum, long LIM, int factor) {
        long res = 0;
        for (int i = 0; i < cnt1; ++i) {
            long p1 = pt1[i];
            long n = p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1 * p1;

            if (n > LIM) {
                break;
            }

            for (int j = 0; j < cnt1; ++j) {
                if (i == j) {
                    continue;
                }

                long p2 = pt1[j];
                long n2 = n * p2 * p2;

                if (n2 > LIM) {
                    break;
                }

                res += suitablesSum[(int) (LIM / n2)] * n2 * factor;
            }
        }
        return res;
    }

    private boolean fillSuitableNumbers(long n, int ind, boolean[] isSuitableNumber, long[] primes, int pcnt) {
        if (n >= isSuitableNumber.length) {
            return false;
        }

        isSuitableNumber[(int) n] = true;

        for (int i = ind; i < pcnt; ++i) {
            if (!fillSuitableNumbers(n * primes[i], i, isSuitableNumber, primes, pcnt)) {
                break;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Tester.test(new Task_233());
    }
}
