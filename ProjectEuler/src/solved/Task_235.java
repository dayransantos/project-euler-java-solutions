package solved;

import tasks.ITask;

//Answer :
public class Task_235 implements ITask {
    double NEED = -600000000000L;
    double EPS = 1e-15;
    public void solving() {
        double l = 1.0;
        double r = 1.003;

        System.out.println(f(l) - NEED);
        System.out.println(f(r) - NEED);

        while (r-l >= EPS) {
            double m = (l+r)/2;

            double fm = f(m);

            if (fm > NEED) {
                l = m;
            } else {
                r = m;
            }
        }


        System.out.println(l);
        System.out.println(r);
        
        System.out.println(f(1.0023221086328762));
    }

    private double f(double r) {
        double rp = 1;
        double s = 0;
        for (int k = 1; k <= 5000; ++k) {
            s += (900-3*k) * rp;
            rp *= r;
        }
        return s;
    }
}
