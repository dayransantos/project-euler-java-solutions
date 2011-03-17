package tasks;

import utils.LongFraction;

//Answer :
public class Task_152_2 implements ITask {
    private static final int MAX_NUM = 35;
    private static final LongFraction half = new LongFraction(1, 2);
    private static final double halfd = 1.0/2.0;
    private static final double EPS = 1e-11;

    double sq[] = new double[MAX_NUM+1];

    double sm[] = new double[MAX_NUM+1];

    private static int res = 0;
    public void solving() {
        for (int i = 2; i <= MAX_NUM; ++i) {
            sq[i] = i*i;
        }

        sm[2] = 1.0/sq[2];
        for (int i = 3; i <= MAX_NUM; ++i) {
            sm[i] = sm[i-1] + 1/sq[i];

        }

        find(0, 2, 0);
    }

    private int path[] = new int[MAX_NUM+1];
    private void find(int ind, int beg, double sum) {
        if (equal(sum, halfd)) {
            res++;
            for (int i = 0; i < ind; ++i) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = beg; i <= MAX_NUM; ++i) {
            double s2 = sum + 1.0/sq[i];
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