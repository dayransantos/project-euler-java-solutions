package tasks;

import java.math.BigDecimal;
import utils.log.Logger;

//Answer :
public class Task_267 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_267());
        Logger.close();
    }
    long bill = 1000000000;
    BigDecimal bbill = BigDecimal.valueOf(bill);
    int n = 1000;

    final double eps = 1e-13;
    public void solving() {
        double mnf = eps/2;
        double mxf = 1-eps/2;

        while (Math.abs(mnf - mxf) > eps) {
            double mf1 = (2 * mnf + mxf) / 3;
            double mf2 = (mnf + 2 * mxf) / 3;

            double p1 = getProb(mf1);
            double p2 = getProb(mf2);


            if (p1 > p2) {
                mxf = mf2;
            } else {
                mnf = mf1;
            }
            System.out.println(mnf);
            System.out.println("    " + mxf);
        }

        System.out.println("------------------------");
        System.out.println(mnf);
        System.out.println(mxf);
    }

    double getProb(double f) {
        double good = 0;
        double all = 0;

        BigDecimal f1 = BigDecimal.valueOf(1 - f);
        BigDecimal f2 = BigDecimal.valueOf(1 + 2 * f);
        BigDecimal f12 = BigDecimal.valueOf((1 - f) * (1 + 2 * f));


        for (int p = 0; p <= n; ++p) {
            int q = n - p;

            BigDecimal b = p > q
                    ? f12.pow(q).multiply(f2.pow(p - q))
                    : f12.pow(p).multiply(f1.pow(q - p));

            if (b.compareTo(bbill) > 0) {
                good++;
            }
            all++;
        }

        return good / all;
    }
}
