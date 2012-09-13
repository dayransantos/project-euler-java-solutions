package reg.ru;

public class Task__14 {
    private final int LIM = 1000000;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__14().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    long ln[] = new long[5000000];

    public void solving() {
        long mx = 1;
        long ml = 1;
        ln[1] = 1;

        for (int i = 2; i <= LIM; ++i) {
            long lni = get(i);

            if (lni > ml) {
                ml = lni;
                mx = i;
            }
        }

        System.out.println(mx);
    }

    private long get(long n) {
        if (n < ln.length && ln[((int) n)] != 0) {
            return ln[((int) n)];
        }

        return 1 + get(next(n));

    }

    private long next(long n) {
        return n % 2 == 0
               ? n / 2
               : 3 * n + 1;
    }
}
