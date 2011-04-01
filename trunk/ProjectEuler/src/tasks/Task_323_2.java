package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

//Answer :
public class Task_323_2 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_323_2());
        Logger.close();
    }

    MathContext mc = MathContext.DECIMAL128;
    int n = 32;
    BigDecimal eps = ONE.divide(p2(70));

    public void solving() {
        System.out.println(eps);
        BigDecimal res = ZERO;
        BigDecimal prevf = ZERO;
        BigDecimal prevpone = ZERO;
        for (int k = 1; ;++k) {
            BigDecimal p2k = p2(k);
            BigDecimal pone = p2k.subtract(ONE, mc).divide(p2k, mc);
            BigDecimal p = pone.pow(n, mc);

            BigDecimal f = ONE.subtract(p, mc).multiply(ONE.subtract(prevf, mc), mc);

            res = res.add(f.multiply(valueOf(k)));
            System.out.println(res + ": " + f);

            if (f.compareTo(eps) < 0) break;

            prevf = f;
        }
        System.out.println(OtherUtils.formatDouble(res.doubleValue(), 10));
    }

    private BigDecimal p2(int k) {
        return new BigDecimal(BigInteger.ONE.shiftLeft(k), mc);
    }
}
