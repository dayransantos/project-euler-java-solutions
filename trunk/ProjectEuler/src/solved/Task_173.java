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

//Answer : 1572729
public class Task_173 implements ITask {
    final static int GIVEN = 1000000;
    public void solving() {
        long res = 0;
//        long mx = (long) sqrt(GIVEN);

        for (int i = 3; ; ++i) {
            if ( (4*i-4) > GIVEN) break;
            for (int j = i-2; j>0 && (i*i - j*j <= GIVEN); j-=2) {
                ++res;
            }
        }

        System.out.println( res );
    }
}