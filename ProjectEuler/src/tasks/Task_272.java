package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
//see: http://oeis.org/A060839 for some thoughts
public class Task_272 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_272());
        Logger.close();
    }

    long LIM = 100000000000L;

    long[] needprimes;
    int np;
    long[] otherprimes;
    int op;

    long sum = 0;
    public void solving() {
        long hiprime = LIM / (7 * 13 * 19 * 31);
        needprimes = MyMath.getPrimesBetween(0, hiprime);
        otherprimes = new long[needprimes.length];

        np = 0;
        op = 0;
        for (long p : needprimes) {
            if (p%3 == 1) {
                needprimes[np++] = p;
            } else {
                otherprimes[op++] = p;
            }
        }

        System.out.println(np);

        find(0, 0, 1);
    }

    private void find(int ind, int cnt, long n) {
        if (n > LIM || np-ind - cnt < 5) {
            return;
        }

        if (cnt == 5) {
            findRest();
            return;
        }

        for (int c = 0; c < 10; ++c) {
            find(ind + 1, c==0 ? cnt+1 : cnt, n * needprimes[ind]);
        }


    }

    private void findRest() {
        //todo:

    }
}
