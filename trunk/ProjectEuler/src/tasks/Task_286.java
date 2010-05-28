package tasks;

import utils.log.Logger;

//Answer :
public class Task_286 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_286());
        Logger.close();
    }

    public void solving() {
//        for (double q = 50.00001; q < 1000; q += 1) {
//            System.out.println(q + " " + getExpected(q));
//        }
//
        System.out.println(getExpected(50.000000000001));

        double ql = 1e-11;
        double qr = 1.0;
        double qm = (qr + qr) / 2;
        double eps = 1e-10;
        double need = 0.02;

        while (qm - ql >= eps) {
            double qrm = (qr+qm)/2;
            double qlm = (qm+ql)/2;

            double res1 = getExpected(qrm);
            double res2 = getExpected(qlm);
            
            if (res1 > need) {
                qm = qr;
            }
        }
    }

    double exp[][] = new double[51][21];
    private double getExpected(double q) {
        exp[0][0] = 1;
        for (int i = 1; i < 50; ++i) {
            for (int j = 0; j <= 20; ++j) {
                double pi1 = 0;
                if (j != 0) {
                    pi1 = exp[i-1][j-1]*(q-i)/q;
                }
                exp[i][j] = exp[i-1][j]*i/q + pi1;
            }
        }

        return exp[50][20];
    }
}
