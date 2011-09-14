package reg.ru;

public class Task_6 {
    private static final long LIM = 100;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_6().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long s1 = 0;
        long s2 = 0;
        for (long i = 1; i <= LIM; ++i) {
            s1 += i*i;
            s2 += i;
        }

        System.out.println(s2*s2 - s1);
    }
}
