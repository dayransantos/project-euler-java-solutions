package tasks;

import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer :
public class Task_351 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_351());
        Logger.close();
    }

//H(5) = 30. H(10) = 138. H(1 000) = 1177848.
    long H = 1000;

    public void solving() {
        long res = -1;

        int xn, rn;
        for (int r = 2; r <= H; ++ r) {
            int rr = 0;
            for (int x = r%2; x <= r; x += 2) {
                if (x == 0) {
                    ++rr;
                    continue;
                }

                int g = gcd(x, r);
                if (g == 1) {
                    continue;
                }
                g /= 2;
                xn = x/2;
                rn = r/2;

//                xn = x/g;
//                rn = r/g;

                if (xn%2 - rn%2 == 0 || g != 1) {// || (g/2!=1)) {
                    ++rr;
                }
            }
            System.out.println(r + ": " + rr);
            res += rr;
        }
        System.out.println(6*(res*2 - 3*H/2 + 2));
    }
}
