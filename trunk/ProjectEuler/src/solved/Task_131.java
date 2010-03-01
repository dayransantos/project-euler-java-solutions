package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;

import tasks.ITask;

//Answer : 173
public class Task_131 implements ITask {
    public void solving() {
        long[] primes = MyMath.getPrimesBetween(0, 1000001);

        long res = 0;
        for (long p : primes) {
            long D = 3*(4*p - 1);
            if (!isExactSquare(D)) continue;
            long d = (long) sqrt(D);
            if ((d-3) % 6 != 0) continue;
//            System.out.println(p);
            res++;
        }

        System.out.println( res );
    }
}
