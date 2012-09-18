package tasks;

import utils.log.Logger;

//Answer :
public class Task_394 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_394());
        Logger.close();
    }

//    public double d = 1;
//    public double d = 2;
    public double d = 7.5;
//    public double d = 40;

    public long n = 10000000L;

    public long zn = (long) (n/d);

    public void solving() {
        double fk = 1;
        double fs = 0;
        for (long k = zn + 1; k <= n; ++k) {
            double k2 = k*k;

            fk = (fk*(k2 - 2*k + 2) + (2*fs + 1) + 2*zn)/k2;
            fs = fs + fk + 1;
        }
        System.out.println(fk);
    }
}
