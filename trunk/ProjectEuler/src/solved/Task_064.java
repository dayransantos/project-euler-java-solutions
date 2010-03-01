package solved;

import tasks.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Stack;
import java.math.BigInteger;
import java.util.HashMap;
import utils.pairs.IntPair;

//Answer : 1322
public class Task_064 implements ITask {

    public void solving() {
        long res = 0;

        for (int n = 2; n <= 10000; ++n) {
            if ( isExactSquare(n) ) continue;
            if ( getCircleLength(n)%2!=0 ) ++res;
        }

        System.out.println(res);
    }

    private int getCircleLength(int n) {
        HashMap<IntPair, Integer> hs = new HashMap<IntPair, Integer>();

        double sqn = sqrt(n);
        int p = 0;
        int q = 1;

        int index = 0;
        while (true) {
            ++index;
            
            IntPair pi = new IntPair(p, q);
            if (hs.containsKey(pi)) {
                int ind = hs.get(pi);
                return index - ind;
            }
            hs.put(pi, index);

            int a = (int) ((p + sqn) / (double) q);
            
            int p1 = q * a - p;
            int q1 = (n - p*p + 2*a*p*q - a*a*q*q) / q;

            p = p1;
            q = q1;
        }
    }
    public static void main(String[] args) {
        Tester.test(new Task_064());
    }
}
