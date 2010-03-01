package solved;

import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import java.math.BigInteger;
import java.math.BigDecimal;

import static utils.OtherUtils.*;
import tasks.ITask;

//Begin with  : http://en.wikipedia.org/wiki/Fibonacci_number#Power_series
//Answer      : 1120149658760
//Explanation : http://projecteuler.net/index.php?section=problems&id=137
public class Task_137 implements ITask {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger FIVE = BigInteger.valueOf(5);
    private static final BigInteger FOUR = BigInteger.valueOf(4);

    public void solving() {
        long k = 2;
        long oldK = 0;
        for (int i = 2; i < 17; ++i) {
            System.out.println( (i-1) + " : " + k );
            long newK = 7*k - (oldK-1);
            oldK = k;
            k = newK;
        }

    }
}
