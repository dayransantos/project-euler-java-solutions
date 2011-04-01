package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

//Answer :
public class Task_323 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323());
        Logger.close();
    }

    int n = 32;
    double eps = 1e-290;
    BigDecimal two = new BigDecimal(2);

//    11.276717951382189: 5.421010862427522E-20
//    11.276717951382189: 2.7635739376302223E-76

    public void solving() {
        double res = 0;
        double prevf = 0;
        BigDecimal prevpone = ZERO;
        for (int k = 1; ;++k) {
            BigDecimal p2k = p2(k);
            BigDecimal pone = p2k.subtract(ONE).divide(p2k);
            BigDecimal p = pone.pow(n);

            double f = ONE.subtract(p).doubleValue();
            f *= (1 - prevf);

            res += f * k;
            System.out.println(res + ": " + f);

            if (f < eps) break;

            prevf = f;
        }
        System.out.println(OtherUtils.formatDouble(res, 10));
    }

    private BigDecimal p2(int k) {
        return new BigDecimal(BigInteger.ONE.shiftLeft(k));

    }
}
