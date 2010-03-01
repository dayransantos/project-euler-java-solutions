package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static java.math.BigInteger.*;

import tasks.ITask;

//Answer : 51161058134250
public class Task_113 implements ITask {
    long d[][] = new long[100][10];
    public void solving() {
        int n = 100;
        for (int i = 0; i < 10; ++i) {
            d[0][i] = 1;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < 10; ++j) {
                for (int k = j; k < 10; ++k) {
                    d[i][j] += d[i-1][k];
                }
            }
        }

        long res = 0;
        long sum = 0, sum1 = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < 10; ++j) {
                res += 2*d[i][j];
                sum1 += d[i][j];
            }
            res += sum;
            sum = sum1;
        }
        res -= 9*n;

        System.out.println("Have not bouncy: " + (res) );
    }
}
