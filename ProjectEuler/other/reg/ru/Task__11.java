package reg.ru;

public class Task__11 {
    private static final int LIM = 500;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__11().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        int n = 1;
        long divc = 1;
        for (int i = 2; divc <= LIM; ++i) {
            n += i;

            divc = getDivCount(n);
        }
        System.out.println(n);
    }

    private long getDivCount(long n) {
        long res = 2;
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                long j = n / i;
                res += (i == j) ? 1 : 2;
            }
        }

        return res;
    }
}
