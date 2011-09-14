package reg.ru;

//Answer :
public class Task_3 {
    private long n = 600851475143L;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_3().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long sqn = (long) Math.sqrt(n);
        //sqrt(n) == 775146.099224527

        long mx = 0;

        for (long i = 3; i <= sqn; i+= 2) {
            if (n % i == 0) {
                long j = n / i;

                if (isPrime(i)) {
                    mx = Math.max(mx, i);
                }

                if (isPrime(j)) {
                    mx = Math.max(mx, j);
                }
            }
        }

        System.out.println(mx);
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
