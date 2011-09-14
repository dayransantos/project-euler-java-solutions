package reg.ru;

//Answer :
public class Task_2 {
    private final long LIM = 4000000;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_2().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long f1 = 1;
        long f2 = 2;

        long res = 0;

        while (f2 < LIM) {
            if (f2%2 == 0) {
                res += f2;
            }

            long f3 = f1 + f2;

            f1 = f2;
            f2 = f3;
        }

        System.out.println(res);
    }
}
