package solved;

import tasks.ITask;

import static utils.MyMath.gcd;

//Answer : 1000023
public class Task_129 implements ITask {
    final long NEED_A = 1000000;

    public void solving() {
        long res = 0;
        for (long n = NEED_A; ; ++n) {
            if (gcd(10, n) != 1) continue;
            long a = A(n);
            if (a > NEED_A) {
                res = n;
                break;
            }
        }

        System.out.println( res );
    }

    private long A(long n) {
        long k = 1;
        long r = 1;

        while (r != 0) {
            r = (10*r+1) % n;
            ++k;
            if (k > NEED_A) return k;
        }

        return k;
    }
}
