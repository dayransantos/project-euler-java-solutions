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

//Answer : 149253
public class Task_130 implements ITask {
    public void solving() {
        long res = 0;
        int cnt = 0;
        for (long n = 2; ; ++n) {
            if (gcd(10, n) != 1) continue;
            if (isPrime(n)) continue;
            long a = A(n);
            if ((n-1)%a == 0 ) {
                System.out.println(n);
                res += n;
                if (++cnt == 25) break;
            }
        }

        System.out.println( "Sum: " + res );
    }

    private long A(long n) {
        long k = 1;
        long r = 1;

        while (r != 0) {
            r = (10*r+1) % n;
            ++k;
            if (k >= n) return n;
        }

        return k;
    }
}
