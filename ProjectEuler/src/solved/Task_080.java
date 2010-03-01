package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;
import static utils.OtherUtils.*;
import tasks.ITask;
import tasks.Tester;

//Answer : 40886
public class Task_080 implements ITask {
    static BigInteger TWO = BigInteger.valueOf(2);
    static BigInteger TEN = BigInteger.TEN;

    public void solving() {
        int sum = 0;
        for (int i = 2; i < 100; ++i) {
            if ( !isExactSquare(i) ) {
                Apint sq = ApintMath.sqrt(ApintMath.pow(new Apint(10), 200).multiply(new Apint(i)))[0];
                String ssq = sq.toString();
                System.out.println( ssq );
                for (int j = 0; j < 100; ++j) {
                    sum += ssq.charAt(j) - '0';
                }
            }
        }
        System.out.println( sum );
    }

    public static void main(String[] args) {
        Tester.test(new Task_080());
    }
}
