package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_381 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_381());
        Logger.close();
    }

        int LIM = 7;
//        int LIM = 100;
//    int LIM = 100000000;
    public void solving() {
        System.out.println(MyMath.inverseEuclid(24, 7));

        long res = 0;
        for (long p : MyMath.getPrimesBetween(5, LIM)) {
            long r = MyMath.inverseEuclid(24, p);
            res += (9*r) % p;
        }
        System.out.println(res);

    }

    private boolean isPrime(int i) {
//        return MyMath.isProbablePrime(i, 3);
        return MyMath.isProbablePrime(i, 3);
    }
}
