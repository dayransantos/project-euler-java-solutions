package tasks;

import utils.log.Logger;

import static utils.Combinatoric.C;

//Answer :
public class Task_323 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323());
        Logger.close();
    }

    int n = 13;

    public void solving() {
        System.out.println(f(1));
    }

    public double f(int k) {
        long den = 1<<k;
        double pden = 1;
        double p = 0;
        double sign = 1;
        for (int i = 1; i <= n; ++i) {
            pden *= den;
            double c = C(i, n).longValue()*(1<<(k-1));
            p += sign*c/pden;
            sign = -sign;
        }

        return p;
    }

}
