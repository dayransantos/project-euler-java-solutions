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

//Answer : 1818 | 1000457
public class Task_086 implements ITask {
    public void solving() {
        long res = 0;


//        int n = 101;
//        for (int i = 1; i < n; ++i) {
//            for (int j = i; j < n; ++j) {
//                for (int k = j; k < n; ++k) {
//                    int m = min( min((i+j)*(i+j) + k*k, (i+k)*(i+k) + j*j), (k+j)*(k+j) + i*i );
//                    if (isExactSquare(m)) res++;
//                }
//            }
//        }

        int m;
        res = 0;
        for (m = 1; res <= 1000000; ++m) {
            for (int i = 1; i <= m; ++i) {
                for (int j = i; j <= m; ++j) {
                    int k = m;
                    int ms = min( min((i+j)*(i+j) + k*k, (i+k)*(i+k) + j*j), (k+j)*(k+j) + i*i );
                    if (isExactSquare(ms)) res++;
                }
            }
        }

        System.out.println( m + " " + res);
    }
}
