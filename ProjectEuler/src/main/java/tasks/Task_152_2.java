package tasks;

import utils.BigFraction;

import java.math.BigInteger;

//Answer :
public class Task_152_2 implements ITask {
    private static final int MAX_NUM = 80;
    private static final double halfd = 1.0/2.0;
    private static final double EPS = 1e-11;

    double usq[] = new double[MAX_NUM+1];

    double sm[] = new double[MAX_NUM+1];

    private static int res = 0;
    public void solving() {
        for (int i = 2; i <= MAX_NUM; ++i) {
            usq[i] = 1.0/(i*i);
        }

        sm[2] = usq[2];
        for (int i = 3; i <= MAX_NUM; ++i) {
            sm[i] = sm[i-1] + usq[i];

        }

        find(0, 2, 0);
        System.out.println(res);
    }

    private int path[] = new int[MAX_NUM+1];
    private void find(int ind, int beg, double sum) {
        if (equal(sum, halfd) && check(ind)) {
            res++;
            for (int i = 0; i < ind; ++i) {
                System.out.print(path[i] + " ");
            }

            System.out.println();

            return;
        }

        for (int i = beg; i <= MAX_NUM; ++i) {
            double s2 = sum + usq[i];
            if (more(s2, halfd)) {
                continue;
            }

            if (more(halfd, s2 + sm[MAX_NUM] - sm[i])) {
                break;
            }

            path[ind] = i;
            find(ind+1, i+1, s2);
        }
    }

    BigInteger ONE = BigInteger.valueOf(1);
    BigInteger TWO = BigInteger.valueOf(2);
    private boolean check(int ind) {
        BigFraction f = BigFraction.ZERO;
        for (int i = 0; i < ind; ++i) {
            f = f.add(new BigFraction(1, path[i]*path[i]));
        }
//        System.out.println("    : " + f);
        return f.numer.equals(ONE) && f.denom.equals(TWO);
    }

    private boolean equal(double a, double b) {
        return Math.abs(a - b) <= EPS;
    }

    private boolean more(double a, double b) {
        return a - b > EPS;
    }

    public static void main(String[] args) {
        Tester.test(new Task_152_2());
    }

}