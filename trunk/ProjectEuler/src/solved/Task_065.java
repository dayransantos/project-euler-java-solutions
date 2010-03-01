package solved;

import static utils.MyMath.*;
import utils.Fraction;
import tasks.ITask;

public class Task_065 implements ITask {
    public void solving() {
        Fraction E = getEFraction(1, 99).add(new Fraction(2, 1) );

        String numer = E.numer.toString();

        System.out.println("E = " + E);

        int res = 0;
        for (int i = 0; i < numer.length(); ++i) {
            res += numer.charAt(i) - '0';
        }
        System.out.println(res);
    }

    private static Fraction getEFraction(int k, int endWith) {
        Fraction res = null;
        if (k%3!=2) {
            res = new Fraction(1, 1);
        } else {
            res = new Fraction(2*(k/3 + 1), 1);
        }

        if (k == endWith) return res.reciprocal();

        return res.add( getEFraction(k+1, endWith) ).reciprocal();
    }
}
