package tasks;

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

//Answer :
public class Task_218 implements ITask {
    long perfect[] = {6, 28, 496, 8128, 33550336L, 8589869056L, 137438691328L, 2305843008139952128L};
    BigInteger bPerf[];
    private long LIM = 10000000000000000L;
    
    public void solving() {
        bPerf = new BigInteger[perfect.length];
        for (int i = 0; i < perfect.length; ++i) {
            bPerf[i] = valueOf(perfect[i]);
        }

        long res = 0;
        for (long c : perfect) {
            if (c > LIM) break;
            long maxm = (long) (sqrt(c / 2) + 2);
            for (int m = 2; m <= maxm;++m) {
                long n2 = c - m*m;
                long n = (long) sqrt(n2);
                if (m%2==n%2 || n*n != n2 || gcd(m,n)!=1) continue;

                long a = 2*m*n;
                long b = m*m - n*n;

                if (!divedesPerfect(a/2, b)) {
                    ++res;
                } else {
                    System.out.println("Isn't: " + a + " " + b + " " + c);
                }
            }
        }

        System.out.println(res);

    }

    public static void main(String[] args) {
        Tester.test(new Task_218());
    }

    private boolean divedesPerfect(long a2, long b) {
        BigInteger ar = valueOf(a2).multiply(valueOf(b));
        for (BigInteger p : bPerf) {
            if (ar.mod(p).equals(ZERO)) return true;
        }
        return false;
    }
}
