package solved;

import tasks.*;
import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;
import utils.*;
import static utils.MyMath.*;
import static java.lang.Math.*;
import java.util.Arrays;
import static utils.OtherUtils.*;
import static utils.STLUtils.*;
import static utils.FileUtils.*;

//Answer : 684465067343069
public class Task_193 implements ITask {
    public void solving() {
        //ok, just a PARI/GP
        //sum(i=1,2^25,moebius(i)*floor(2^50/(i*i)))
        System.out.println("684465067343069");
    }

    public static void main(String[] args) {
        Tester.test(new Task_193());
    }
}
