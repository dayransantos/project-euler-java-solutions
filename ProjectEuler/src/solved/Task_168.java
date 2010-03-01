package solved;

import tasks.*;
import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import static utils.FileUtils.*;

//Answer : 59206
public class Task_168 implements ITask {
    public void solving() {
        int res = 0;
        for (int di = 2; di < 10; ++di) {
            BigInteger d = BigInteger.valueOf(di);
            for (int ki = di; ki < 10; ++ki ) {
                BigInteger k = BigInteger.valueOf(ki);

                BigInteger r = k;
                BigInteger p10 = ONE;
                int t = 0;
                while (true) {
                    ++t;
                    BigInteger rn = r.multiply(d);

                    if ((rn.toString().substring(1) + rn.toString().charAt(0)).equals(r.toString())) {
                        break;
                    }

                    p10 = p10.multiply(TEN);
                    r = rn.mod(p10).multiply(TEN).add(k);
                }

                r = r.mod(TEN.pow(5));
                int ri = r.intValue();
                for (int i = 0; i < 99 / t; ++i ) {
                    res = (res + ri) % 100000;
                }
            }
        }

        //multipliers of 1
        for (int d = 1; d < 10; ++d) {
            int ri = d;
            for (int i = 1; i <= 99; ++i) {
                ri = (ri*10 + d)%100000;
                res = (res + ri) % 100000;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_168());
    }
}
