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

//Answer : 5437849
public class Task_216 implements ITask {

    private long LIM = 50000000;

    public void solving() {
        long res = 0;
        for (long n = 1; n <= LIM; ++n) {
            if (n%1000000 == 0) {
                System.out.println("Progress: " + n);
            }
            if (isProbablePrime(n)) {
                ++res;
            }
        }
        System.out.println(res);
    }

    private boolean isProbablePrime(long n) {
        return valueOf(2*n).multiply(valueOf(n)).subtract(ONE).isProbablePrime(3);
    }

    public static void main(String[] args) {
        Tester.test(new Task_216());
    }
}
