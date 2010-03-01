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

//Answer : 333082500
public class Task_120 implements ITask {
    public void solving() {
        long res = 0;

        for (int i = 3; i <= 1000; ++i) {
            res += getMaxRem(i);
        }
        System.out.println( res );
    }

    private long getMaxRem(int a) {
        return a*  ((a+1)/2-1)*2;
    }
}
