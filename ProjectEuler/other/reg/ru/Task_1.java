package reg.ru;

//Answer :
public class Task_1 {
    private final int LIM = 1000;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_1().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        int res = 0;
        for (int i = 1; i < LIM; ++i) {
            if (i % 3 == 0 || i % 5 == 0) {
                res += i;
            }
        }

        System.out.println(res);
    }
}
