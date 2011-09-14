package reg.ru;

//Answer :
public class Task_7 {
    private static final int ind = 10001;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_7().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long last = 2;
        int cnt = 1;
        for (long i = 3; cnt < ind; i+= 2) {
            if (isPrime(i)) {
                last = i;
                cnt++;
            }
        }

        System.out.println(last);
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
