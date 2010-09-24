package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import tasks.ITask;

//Answer : 2772
public class Task_085 implements ITask {
    public void solving() {
        long res = 0;

        long dr = 2000000;
        for (long a = 1; a <= 2000000; ++a) {
            for (long b = 1; b <= 2002000/a; ++b) {
                if (a*b > 3000) continue;
                long f = abs(2000000-a*b*(a+1)*(b+1)/4);
                if (f < dr) {
                    dr = f;
                    res = a*b;
                }
            }
        }

        System.out.println(res);
    }
}