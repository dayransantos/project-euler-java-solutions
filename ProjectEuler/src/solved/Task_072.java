package solved;

import tasks.ITask;

import java.util.ArrayList;

import static utils.MyMath.getPrimeDivisors;
import static utils.MyMath.phi;

//Answer : 303963552391
public class Task_072 implements ITask {
    public void solving() {
        long count = 1000000;
        long res = 0;

        for (int i = 2; i <= count; ++i) {
            res += phi(i);
        }

        System.out.println(res);
    }

    public void solving0() {
        long count = 1000000;

        long res = count*(count-1) / 2;
        for (int i = 2; i <= count; ++i) {
            ArrayList<Long> divs = getPrimeDivisors(i);
            res -= getCount(count-i, divs, 0, 1);
        }


        System.out.println(res);
    }

    private long getCount(long count, ArrayList<Long> divs, int index, long currLcm) {
        long res = 0;
        for (int i = index; i < divs.size(); ++i) {
            long newLcm = currLcm * divs.get(i);
            res += count / newLcm;

            res -= getCount(count, divs, i + 1, newLcm);
        }

        return res;
    }
}
