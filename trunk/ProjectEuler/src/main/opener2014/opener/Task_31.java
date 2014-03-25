package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Answer :
public class Task_31 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_31());
        Logger.close();
    }

    Set<Integer> primes = new HashSet<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251));
    boolean isprime[] = new boolean[258];
    int group[] = new int[15];
    int grcnt = 0;
    double probs[][] = new double[257][7];
    double res[] = new double[257];

    public void solving() {
        BigInteger lcm = BigInteger.ONE;

        for (int i = 1; i <= 256; ++i) {
            isprime[i] = primes.contains(i);
            if (!isprime[i - 1] && !isprime[i] && !isprime[i + 1]) {
                grcnt = 0;
                System.out.println();

                processCurrentGroup();
            }
            System.out.print(i + " ");
            group[grcnt++] = i;
        }

        for (int n = 1; n <= 256; ++n) {
            double res;
            if (primes.contains(n)) {
                res = n % 5 + 1;
            } else if (n % 2 == 1) {
                res = 0;
                for (int s = 1; s <= 6; ++s) {
                    res += (30.0 / 36.0 * s);
                    res += 6.0 / 36.0 * (
                            0.5 * s + 0.5 * (
                                    (2 + 3 + 4 + 5 + 6 + 7) * 1.0 / 6.0
                            )
                    );
                }
            } else {
                res = 0;
            }
            if (ok(res)) {
                lcm = MyMath.lcm(lcm, BigInteger.valueOf(n));
            }
        }

        System.out.println(lcm);
    }

    private void processCurrentGroup() {
        if (grcnt == 1) {
            int n = group[0];
            assert !isprime[n];
            if (n > 2 && n < 255 && !isprime[n - 1] && !isprime[n + 1] && !isprime[n - 2] && !isprime[n + 2]) {
                double r = 0;
                if (n % 2 == 1) {
                    for (int s = 1; s <= 6; ++s) {
                        r += (30.0 / 36.0 * s);
                        r += 6.0 / 36.0 * (
                                0.5 * s + 0.5 * (
                                        (2 + 3 + 4 + 5 + 6 + 7) * 1.0 / 6.0
                                )
                        );
                    }
                } else {
                    // чётный
                    for (int s = 1; s <= 6; ++s) {
                        r += (30.0 / 36.0 * (n%5 + 1));
                        r += 6.0 / 36.0 * (
                                0.5 * s + 0.5 * (
                                        (1 + 2 + 3 + 4 + 5 + 6) * 1.0 / 6.0
                                )
                        );
                    }
                }
                res[n] = r;
            }
            return;
        }

        validCount = 0;
        process(0);
    }

    int moves[] = new int[15];
    double scores[] = new double[15];
    int validCount
    private void process(int ind) {
        if (ind == grcnt) {
            countScores();
            //todo:
            return;
        }

        for (int s = 1; s <= 6; ++s) {
            moves[ind] = s;
            process(ind+1);
        }
    }

    private void countScores() {
        for (int i = 0; i < grcnt; ++i) {
            if (i == 0 || i == grcnt - 1) {
                //крайние непростые
                scores[i] = estimateEdge(group[i], moves[i]);
            } else {
                scores[i] = countScore(group[i], moves[i-1], moves[i], moves[i+1]);
            }
        }
    }

    private double countScore(int n, int prev, int self, int next) {
        if (prev == next) {
            return 0.5 * self + 0.5 * (1 + 2 + 3 + 4 + 5 + 6 + (n%2==1?6:0));
        } else {
            //todo:
            return 0;
        }
    }

    private double estimateEdge(int n, int s) {
        if (n%2 == 1) {
            return 5.0 / 6.0 * s + 1.0/6.0 * (0.5 * s + 0.5 * (2 + 3 + 4 + 5 + 6 + 7)*1.0/6.0);
        } else {
            return 5.0 / 6.0 * (n%5 + 1) + 1.0/6.0 * (0.5 * s + 0.5 * (1 + 2 + 3 + 4 + 5 + 6)*1.0/6.0);
        }
    }

    private boolean ok(double res) {
        String fr = OtherUtils.formatDouble(res, 10);
        int ind = fr.indexOf(".");
        char d = fr.charAt(ind + 2);
        System.out.println(fr + ": " + (d == '1'));
        return d == '1';
    }
}
