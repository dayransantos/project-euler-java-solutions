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

//Answer : 1.710637717
public class Task_197 implements ITask {
    public void solving() {
//        double u = -1;
//        while (true) {
//            System.out.println(u);
//            u = floor(exp((30.403243784-u*u) * log(2))) * 1e-9;
//        }
        //just notice, that sequence quickly came to alternating these two values
        System.out.println(1.029461839 + 0.681175878);
    }

    public static void main(String[] args) {
        Tester.test(new Task_197());
    }
}
