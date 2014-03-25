package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Answer :
public class Task_31 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_31());
        Logger.close();
    }

    Set<Integer> primes = new HashSet<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251));
    
    public void solving() {
        BigInteger lcm = BigInteger.ONE;
        for (int n = 1; n <= 256; ++n) {
            double res;
            if (primes.contains(n)) {
                res = n % 5 + 1;
            } else if (n % 2 == 1) {
                res = 0;
                for (int s = 1; s <= 6; ++s) {
                    res += (30.0 / 36.0 * s);
                    res += 6.0/36.0 * (
                            0.5*s + 0.5*(
                                    (2+3+4+5+6+7)*1.0/6.0
                            )
                    );
                }
            } else {
                res = 0;
            }
            if (ok(res)) {
                lcm = MyMath.lcm(lcm, BigInteger.valueOf(n));
            }
        }

        System.out.println(lcm);
    }

    private boolean ok(double res) {
        String fr = OtherUtils.formatDouble(res, 10);
        int ind = fr.indexOf(".");
        char d = fr.charAt(ind + 2);
        System.out.println(fr + ": " + (d == '1'));
        return d == '1';
    }
}
