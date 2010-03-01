package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import tasks.ITask;

//Answer : 14234
public class Task_091 implements ITask {
    public void solving() {
        int res = 0;

        int sz = 50;
        for (int x1 = 0; x1 <= sz; ++x1) {
            for (int y1 = 0; y1 <= sz; ++y1) {
                for (int x2 = 0; x2 <= sz; ++x2) {
                    for (int y2 = 0; y2 <= sz; ++y2) {
                        if ( isRight(x1, y1, x2, y2) ) {
                            ++res;
                        }
                    }
                }
            }
        }

        System.out.println(res/2);
    }

    private boolean isRight(int x1, int y1, int x2, int y2) {
        if (x2*y1 == x1*y2) {
            return false;
        }
        return check( x1, y1, x2, y2 ) || check( x2-x1, y2-y1, x1, y1 ) || check( x2-x1, y2-y1, x2, y2 );
    }

    private boolean check(int x1, int y1, int x2, int y2) {
        return x1*x2 + y1*y2 == 0;
    }
}
