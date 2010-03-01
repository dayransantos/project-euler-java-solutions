package solved;

import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import tasks.ITask;

//Answer : 26241
public class Task_058 implements ITask {
    public void solving() {
        int all = 1;
        int primes = 0;

        int i;
        for (i = 3; ; i += 2) {
            int c1 = (i-2)*(i-2) + (i-1);
            int c2 = c1 + (i-1);
            int c3 = c2 + (i-1);
            all += 4;

            if ( isPrime(c1) ) ++primes;
            if ( isPrime(c2) ) ++primes;
            if ( isPrime(c3) ) ++primes;

            if (primes*100/all < 10) break;
        }

        System.out.println(i);
    }
}
