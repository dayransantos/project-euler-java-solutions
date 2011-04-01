package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ONE;
import static utils.Combinatoric.C;

//Answer :
public class Task_323 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323());
        Logger.close();
    }

    int n = 32;
    double eps = 1e-18;

//       6.3551758451

    public void solving() {
        double res = 0;
        for (int k = 1; ;++k) {
            BigDecimal p2k = p2(k);
            BigDecimal pone = p2k.subtract(ONE).divide(p2k);
            BigDecimal p = pone.pow(n);

            double f = ONE.subtract(p).doubleValue();
            f *= pz(k-1);

            res += f * k;
            System.out.println(res + ": " + f);

            if (f < eps) break;
        }
        System.out.println(OtherUtils.formatDouble(res, 10));
    }

    private BigDecimal p2(int k) {
        return new BigDecimal(BigInteger.ONE.shiftLeft(k));
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

    public double pz(int k) {
        BigDecimal p = BigDecimal.ZERO;
        for (int i = 1; i <= n; ++i) {
            p = p.add(M(i).divide(p2(k*i)));
        }

        return p.doubleValue();
    }
}
