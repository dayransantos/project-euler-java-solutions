package tasks;

import utils.log.Logger;

import static java.lang.Math.abs;

//Answer :
public class Task_461 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_461());
        Logger.close();
    }

    double pi = Math.PI;
    double denom = 10000;
    
    double best = 0;
    double beste = 100;
    long ba;
    long bb;
    long bc;
    long bd; 
    public void solving() {
        for (int a = 1; ; ++a) {
            System.out.println("Progress A: " + a);
            double sa = f(a);
            if (sa > pi && sa - pi > beste) break;
            for (int b = a; ; ++b) {
                System.out.println("Progress B: " + b);
                double sb = sa + f(b);
                if (sb > pi && sb - pi > beste) break;
                for (int c = b; ; ++c) {
                    double sc = sb + f(c);
                    if (sc > pi && sc - pi > beste) break;
                    for (int d = c; ; ++d) {
                        double sd = sc + f(d);
                        double newe = abs(sd - pi);
                        if (newe < beste) {
                            beste = newe;
                            best = sd;
                            ba = a;
                            bb = b;
                            bc = c;
                            bd = d;
                            System.out.println("New best:");
                            System.out.println(best);
                            System.out.println(beste);
                            System.out.println(ba);
                            System.out.println(bb);
                            System.out.println(bc);
                            System.out.println(bd);
                            System.out.println(ba*ba + bb*bb + bc*bc + bd*bd);
                        } else if (sd > pi) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(best);
        System.out.println(beste);
        System.out.println(ba);
        System.out.println(bb);
        System.out.println(bc);
        System.out.println(bd);
    }

    private double f(double a) {
        return Math.exp(a/denom) - 1;
    }
}
