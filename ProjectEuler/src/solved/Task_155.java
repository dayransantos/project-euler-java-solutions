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
import static utils.FileUtils.*;
import static utils.LongFraction.*;

//Answer : 3857447
//Time   : 6 min
public class Task_155 implements ITask {
    int n = 18;

    TreeSet<LongFraction> all[] = new TreeSet[n];

    public void solving() {
        all[0] = new TreeSet<LongFraction>();
        all[0].add(ONE);

        for (int k = 1; k < n; ++k) {
            all[k] = new TreeSet<LongFraction>();
            for (int i = 0; i < k && k-i-1>=i; ++i) {
                for (LongFraction p1 : all[i]) {
                    for (LongFraction p2 : all[k-i-1]) {
                        all[k].add(p1.add(p2));
                        all[k].add( p1.reciprocal().add(p2.reciprocal()).reciprocal() );
                    }
                }
            }
        }
        TreeSet<LongFraction> r = new TreeSet<LongFraction>();
        for (int i = 0; i < n; ++i) {
            for (LongFraction p : all[i]) {
                r.add(p);
            }
        }

        System.out.println(r.size());
    }

    public static void main(String[] args) {
        Tester.test(new Task_155());
    }
}
