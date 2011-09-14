package reg.ru;

import java.math.BigInteger;

public class Task_5 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_5().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long res = 1;
        for (long i = 1; i <= 20; ++i) {
            res = lcm(res, i);
        }
        System.out.println(res);
    }

    private long lcm(long a, long b) {
        return lcm(BigInteger.valueOf(a), BigInteger.valueOf(b)).longValue();
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);
    }
}
