package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
//see: http://oeis.org/A060839 for some thoughts
public class Task_272_bf implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_272_bf());
        Logger.close();
    }

    long LIM = 30000000;

    long[] needprimes;
    int np;
    long[] otherprimes;
    int op;

    long sum = 0;
    public void solving() {
//        long hiprime = LIM / (7 * 13 * 19 * 31);
//        needprimes = MyMath.getPrimesBetween(0, hiprime);
//        otherprimes = new long[needprimes.length];

        for (long n = 7*13*19*31*37; n <= LIM; ++n) {
            int cnt = 0;
            for (long p : MyMath.getPrimeDivisors(n)) {
                if (p%3==1) {
                    ++cnt;
                    if (cnt > 5) {
                        break;
                    }
                }
            }
            if (cnt == 5) {
                sum += n;
                System.out.println(n);
            }
        }
        System.out.println(sum);
        //10000000 -> 513966362
        //20000000 -> 4055151549
        //30000000 -> 13166591472
    }
}