package solved;

import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import tasks.ITask;

//Answer : 378158756814587
public class Task_164 implements ITask {

    public void solving() {
        long res[][][] = new long[23][10][10];
        for (int i = 1; i <= 9; ++i) {
            for (int j = 0; j <= 9; ++j) {
                if (i + j > 9) {
                    continue;
                }
                res[1][i][j] = 1;
            }
        }

        for (int st = 2; st <= 21; ++st) {
            for (int i = 0; i <= 9; ++i) {
                for (int j = 0; j <= 9; ++j) {
                    int left = 9 - i - j;
                    for (int k = 0; k <= left; ++k) {
                        res[st][j][k] += res[st - 1][i][j];
                    }
                }
            }
        }

        long r = 0;
        for (int i = 0; i <= 9; ++i) {
            for (int j = 0; j <= 9; ++j) {
                r += res[19][i][j];
            }
        }

        System.out.println(r);
    }
}
