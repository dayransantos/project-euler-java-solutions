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

//Answer : 1259187438574927161
public class Task_234 implements ITask {
    long MAX = 999966663333L;

    public void solving() {
        long pr[] = getPrimesBetween(0, 1000004);

        long cnt = 0;
        long sum = 0;
        for (int i = 0; i < pr.length - 1; ++i) {
            long pr1 = pr[i];
            long pr2 = pr[i+1];

            long pn1 = pr1*pr1;
            long pn2 = pr2*pr2;

            if (pn2 > MAX) pn2 = MAX;

            long dc1 = divCnt(pn2-1, pr1) - divCnt(pn1, pr1);
            long dc2 = divCnt(pn2-1, pr2) - divCnt(pn1, pr2);


            long ds1 = ariphmSum(pr1*(pr1+1), +pr1, dc1);
            long ds2 = ariphmSum((pn1/pr2+1)*pr2, pr2, dc2);
             //double counting for pr1*pr2
            if (dc1+dc2 > 0 && pr1*pr2 < pn2) {
                dc1 -=2;
                ds1 -=2*pr1*pr2;
            }

            cnt += dc1 + dc2;
            sum += ds1 + ds2;
        }

        System.out.println( cnt );
        System.out.println( sum );
    }

    public long divCnt(long n, long p) {
        return n/p;
    }

    private long ariphmSum(long a1, long d, long n) {
        return (a1 + a1 + (n-1)*d)*n/2;
    }
}
