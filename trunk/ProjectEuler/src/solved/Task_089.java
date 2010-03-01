package solved;

import java.util.*;
import java.math.*;

import utils.*;
import static utils.MyMath.*;

import static java.lang.Math.*;
import java.util.Arrays;

import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import org.omg.CORBA.TypeCode;
import tasks.ITask;

//Answer : 743
public class Task_089 implements ITask {
    String bases[] = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    int values[] = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    public void solving() {
        ArrayList<String> all = FileUtils.readLines("files/roman.txt");
        int oldlen = 0;
        int len = 0;
        for (String numb : all) {
            oldlen += numb.length();
            len += shortest(numb).length();
        }

        System.out.println(oldlen - len);
    }

    private String shortest(String numb) {
        int value = 0;
        for (int i = 0; i < bases.length; ++i) {
            while (numb.startsWith(bases[i])) {
                value += values[i];
                numb = numb.substring(bases[i].length());
            }
        }

        String res = "";
        for (int i = 0; i < bases.length; ++i) {
            while (value >= values[i]) {
                value -= values[i];
                res += bases[i];
            }
        }

        return res;
    }
}
