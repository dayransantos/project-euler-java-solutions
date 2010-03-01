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

//Answer : 0.5731441
public class Task_205 implements ITask {
    public void solving() {
        long ps[] = new long[37];
        long cs[] = new long[37];

        fill(ps, 9, 4);
        fill(cs, 6, 6);

        long pwin = 0;
        long call = 0;
        long pall = 0;
        for (int i = 0; i < 37; ++i) {
            pwin += ps[i] * call;
            call += cs[i];
            pall += ps[i];
        }

        System.out.println(pwin / (double) (call * pall));
    }

    private void fill(long[] s, long cnt, long mx) {
        s[0] = 1;
        for (long st = 0; st < cnt; ++st) {
            long s2[] = new long[37];

            for (int j = 1; j <= mx; ++j) {
                for (int i = 0; i <= 36-j; ++i) {
                    s2[i + j] = s2[i + j] + s[i];
                }
            }
            System.arraycopy(s2, 0, s, 0, 37);
        }
    }
}