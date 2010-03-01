package solved;

import tasks.*;
import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;

//Answer : 2944730
public class Task_204 implements ITask {
    private static int LIMIT = 1000000000;
    private static int MAX = 100;

    boolean isHumming[] = new boolean[LIMIT+1];

    public void solving() {
        long primes[] = getPrimesBetween(0, MAX);

        int cnt = 0;
        isHumming[1] = true;
        for (int i = 1; i <= LIMIT; ++i) {
            if (!isHumming[i]) {
                cnt++;
            } else {
                for (long p : primes) {
                    long hn = i*p;
                    if (hn <= LIMIT) {
                        isHumming[(int)hn] = true;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println( LIMIT - cnt );
    }
}
