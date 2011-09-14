package reg.ru;

import java.math.BigInteger;

public class Task__20 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__20().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        BigInteger f = BigInteger.ONE;
        for (int i = 2; i <= 100; ++i) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        System.out.println(f);

        String fs = f.toString();

        int s = 0;
        for (int i = 0; i < fs.length(); ++i) {
            s += Integer.parseInt(fs.substring(i, i + 1));
        }
        System.out.println(s);
    }
}
