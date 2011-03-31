package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;

import static java.math.BigInteger.ONE;
import static utils.Combinatoric.C;

//Answer :
public class Task_323 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323());
        Logger.close();
    }

    int n = 3;
    double eps = 1e-12;

    public void solving() {
        System.out.println(f(1));
        double res = 0;
        for (int k = 1; ;++k) {
            double f = f(k);

            res += f;
            System.out.println(res + ": " + f);
            if (f < eps) break;
        }
        System.out.println(OtherUtils.formatDouble(res, 10));
    }

    BigDecimal[] ms = new BigDecimal[n + 1];
    private BigDecimal M(int i) {
        if (ms[i] == null) {
            ms[i] = new BigDecimal(C(n, i));
            if (i%2==0) {
                ms[i] = ms[i].negate();
            }
        }
        return ms[i];
    }

    public double f(int k) {
        BigDecimal p = BigDecimal.ZERO;
        for (int i = 1; i <= n; ++i) {
            p = p.add(M(i).divide(new BigDecimal(ONE.shiftLeft(k * i))));
        }

        return k - p.multiply(new BigDecimal(k)).doubleValue();
    }
}
