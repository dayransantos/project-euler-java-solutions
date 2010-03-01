package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import tasks.ITask;

//Answer : 248155780267521
public class Task_119 implements ITask {
    public void solving() {
        long res = 0;

        ArrayList<Long> all = new ArrayList<Long>();
        for (int n = 2; n < 100; ++n) {
            long p = n;
            for (;p < Long.MAX_VALUE/101; ) {
                p *= n;
                if ( digSum(p)==n ) {
                    all.add(p);
                }
            }
        }

        Collections.sort(all);

        System.out.println( all.get(29) );
    }

    private int digSum(long n) {
        int res = 0;
        while (n!=0) {
            res += n%10;
            n /= 10;
        }
        return res;
    }
}
