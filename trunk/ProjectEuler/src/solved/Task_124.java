package solved;

import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import java.util.ArrayList;

import static utils.OtherUtils.*;
import utils.pairs.LongPair;
import tasks.ITask;

//Answer : 21417
public class Task_124 implements ITask {
    public void solving() {
        final int MAX_NUM = 100000;
        int res = 0;

        LongPair all[] = new LongPair[MAX_NUM+1];

        for (int i = 1; i <= MAX_NUM; ++i) {
            all[i] = new LongPair( rad(i), i );
        }

        Arrays.sort( all, 1, MAX_NUM+1 );

        System.out.println( all[10000].b );
    }

    private long rad(int n) {
        int prod = 1;
        for ( long div : getPrimeDivisors( n ) ) prod *= div;
        return prod;
    }
}
