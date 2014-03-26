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
public class Task_31_2_sides_only implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_31_2_sides_only());
        Logger.close();
    }

    Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251));
    boolean isprime[] = new boolean[258];
    int group[] = new int[15];
    int grcnt = 0;
    double probs[][] = new double[257][7];
    double res[] = new double[257];

    public void solving() {
        Arrays.fill(res, -1);
        for (int i = 1; i <= 256; ++i) {
            for (int s = 1; s <= 6; ++s) {
                probs[i][s] = 1.0 / 6.0;
            }

            isprime[i] = primes.contains(i);
            if (grcnt != 0 && !isprime[i - 1] && !isprime[i] && !isprime[i + 1]) {
                System.out.println();
                processCurrentGroup();
                grcnt = 0;
            }
            System.out.print(i + " ");
            group[grcnt++] = i;
        }
        processCurrentGroup();
        System.out.println();

        BigInteger lcm = BigInteger.ONE;
        for (int n = 1; n <= 256; ++n) {
            double r = res[n];
            if (r < 0) {
                r = 0;

                int left = n == 1 ? 256 : n - 1;
                int right = n == 256 ? 1 : n + 1;
                double prob = 1;
                for (int s = 1; s <= 6; ++s) {
                    prob *= probs[left][s] * probs[right][s];
                }
                for (int s = 1; s <= 6; ++s) {
                    r += estimateNormal(n, s, prob);
                }
            }
            if (ok(r)) {
                lcm = MyMath.lcm(lcm, BigInteger.valueOf(n));
            }
        }

        System.out.println(lcm);
    }

    private void processCurrentGroup() {
        if (grcnt == 1) {
            assert !isprime[group[0]];
            return;
        }

        validCount = 0;
        for (int i = 0; i < grcnt; ++i) {
            scores[i] = 0;
        }
        Arrays.fill(edgeMovesProbs[0], 0);
        Arrays.fill(edgeMovesProbs[1], 0);
        process(0);

        System.out.println("Valid count: " + validCount);

        for (int i = 0; i < grcnt; ++i) {
            int n = group[i];
            if (isprime[n]) {
                res[n] = n % 5 + 1;
            } else {
                res[n] = scores[i] / validCount;
            }
        }

        for (int s = 1; s <= 6; ++s) {
            probs[group[0]][s] = edgeMovesProbs[0][s] / validCount;
            probs[group[grcnt - 1]][s] = edgeMovesProbs[1][s] / validCount;
        }
    }

    int moves[] = new int[15];
    double scores[] = new double[15];
    double varScores[] = new double[15];
    double edgeMovesProbs[][] = new double[2][7];
    double variantProbability;
    int validCount;

    private void process(int ind) {
        if (ind == grcnt) {
            variantProbability = 1;

            if (countVariantScore()) {
                for (int i = 0; i < grcnt - 1; ++i) {
                    scores[i] += varScores[i] * variantProbability;
                }
                ++validCount;
            }
            return;
        }

        for (int s = 1; s <= 6; ++s) {
            moves[ind] = s;
            process(ind + 1);
        }
    }

    private boolean countVariantScore() {
        for (int i = 0; i < grcnt - 1; ++i) {
            varScores[0] = 0;
            if (isprime[group[i]]) {
                if (estimateInner(group[i], moves[i - 1], moves[i], moves[i + 1]) < 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < grcnt; ++i) {
            //крайние непростые
            if (i == 0) {
                varScores[i] += estimateEdge(group[i], moves[i], moves[i + 1]);
            } else if (i == grcnt - 1) {
                varScores[i] += estimateEdge(group[i], moves[i], moves[i - 1]);
            } else {
                varScores[i] += estimateInner(group[i], moves[i - 1], moves[i], moves[i + 1]);
            }
        }
        edgeMovesProbs[0][moves[0]] += variantProbability;
        edgeMovesProbs[1][moves[grcnt - 1]] += variantProbability;
        return true;
    }

    private double estimateInner(int n, int prev, int self, int next) {
        int n51 = n % 5 + 1;
        if (isprime[n]) {
            if (prev == next) {
                // 0.5 => self
                // 0.5 => 
                //    n==2 => one of {1..6}
                //    n!=2 => one of {2..7}
                if (n51 == 1) {
                    if (self != -1) {
                        variantProbability = 0;
                        return -1;
                    } else {
                        variantProbability *= 0.5;
                    }
                } else {
                    double prob = self == n51 ? 0.5 : 0;
                    prob += 0.5 * 1.0/6.0;
                    variantProbability *= prob;
                }

                return n51;
            } else {
                if (n == 2 || self == n51) {
                    return n51;
                }

                return -1;
            }
        } else {
            if (prev == next) {
                return estimateNormal(n, self, 1);
            } else {
                return n % 2 == 1 ? self : n51;
            }
        }
    }

    private double estimateEdge(int n, int self, int next) {
        return estimateNormal(n, self, 1.0 / 6.0);
    }

    private double estimateNormal(int n, int s, double probSameSides) {
        double probDiffSides = 1 - probSameSides;
        if (n % 2 == 1) {
            return probDiffSides * s +
                   probSameSides * (
                           0.5 * s + 0.5 * (
                                   (2 + 3 + 4 + 5 + 6 + 7) * 1.0 / 6.0
                           )
                   );
        } else {
            return (probDiffSides * (n % 5 + 1)) +
                   probSameSides * (
                           0.5 * s + 0.5 * (
                                   (1 + 2 + 3 + 4 + 5 + 6) * 1.0 / 6.0
                           )
                   );
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
