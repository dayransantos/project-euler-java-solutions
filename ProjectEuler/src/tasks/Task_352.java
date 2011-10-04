package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.util.Locale;

import static java.lang.Math.min;

//Answer :
public class Task_352 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_352());
        Logger.close();
    }

    int N = 10000;
    double p;
    double fs[][][] = new double[2][2][N+1];
    double helthyprob[] = new double[N+1];

    public void solving() {

        double res = 0;
        for (int i = 1; i <= 50; ++i) {
            p = i * 0.01;
            OtherUtils.deepFillDouble(fs, -1);

            helthyprob[0] = 1;
            for (int j = 1; j < helthyprob.length; ++j) {
                helthyprob[j] = helthyprob[j - 1] * (1-p);
            }

            System.out.println(String.format(Locale.ENGLISH, "%.2f : %f", p, find(N, 0, 0)));
            res += find(N, 0, 0);
        }

        System.out.println(res);
    }

    private double find(int n, int shouldDivide, int infected) {
        if (infected==1 && n == 1) {
            return 0;
        }

        if (fs[shouldDivide][infected][n] != -1) {
            return fs[shouldDivide][infected][n];
        }

        double res;
        if (shouldDivide == 0) {
            if (infected == 0) {
                res = min(helthyprob[n] + (1 - helthyprob[n]) * (1 + find(n, 1, 1)), find(n, 1, 0));
            } else {
                res = find(n, 1, 1);
            }
        } else {
            res = n;

            if (infected == 0) {
                for (int i = 1; i < n; ++i) {
                    res = min(res, find(i, 0, 0) + find(n - i, 0, 0));
                }
            } else {
                for (int i = 1; i < n; ++i) {
                    res = min(res,
                              find(i, 0, 0)
                              + helthyprob[i] * find(n - i, 0, 1)
                              + (1 - helthyprob[i]) * find(n - i, 0, 0)
                    );
                }
            }
        }

        return fs[shouldDivide][infected][n] = res;
    }
}
