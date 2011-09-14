package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import static java.lang.Math.max;

//Answer :
public class Task_339 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_339());
        Logger.close();
    }

    public int n = 5;
    public void solving() {
        OtherUtils.deepFillDouble(p, -1);
        System.out.println(find(n, n, 1));
    }

    double p[][] = new double[2*n+1][2*n+1];
    private double find(int w, int b, double k) {
        if (k < 1e-5) {
            return 0;
        }
        if (b == 0) {
            return 0;
        }
        if (w == 0) {
            return b;
        }

        if (p[w][b] != -1) {
            return p[w][b];
        }

        double all = w + b;
        double wb = 0;
        double bb = 0;
        for (int i = 0; i <= w; ++i) {
            if (i != w) {
                wb = max(wb, find(w - 1 - i, b + 1, k*w/all));
            }
            bb = max(bb, find(w + 1 - i, b - 1, k*b/all));
        }

        return p[w][b] = w/all * wb + b/all * bb;
    }
}
