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

//Answer : 10057761
public class Task_139 implements ITask {
    public int LIM = 100000000;
    public void solving() {
        long res = 0;
        long maxm = (long) (sqrt(LIM / 2) + 2);
        for (int m = 2; m <= maxm;++m) {
            for  (int n = m%2==0?1:2; n < m; n+=2) {
                if (gcd(m,n) == 1) {
                    long a = 2*m*n;
                    long b = m*m - n*n;
                    long c = m*m + n*n;

                    if (c%(abs(a-b))==0) {
                        long p = a+b+c;
                        res += LIM / p;
                    }
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_139());
    }
}
