package reg.ru;

public class Task__10 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__10().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long s = 2;
        for (long i = 3; i < 2000000; i += 2) {
            if (isPrime(i)) {
                s += i;
            }
        }

        System.out.println(s);
    }

    private boolean isPrime(long k) {
        long sqk = (long) (Math.sqrt(k) + 1);
        for (long i = 3; i < sqk; ++i) {
            if (k%i == 0) {
                return false;
            }
        }
        return true;
    }
}
