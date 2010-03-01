package solved;

import tasks.*;
import java.util.*;
import java.math.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;

//Answer : 86226
public class Task_228 implements ITask {
    public void solving() {
        TreeSet<Fraction> all = new TreeSet<Fraction>();

        for (int n = 1864; n <= 1909; ++n) {
            for (int i = 0; i < n; ++i) {
                all.add( new Fraction(360*i, n) );
            }
        }

        System.out.println(all.size());
    }

    public static void main(String[] args) {
        Tester.test(new Task_228());
    }
}
