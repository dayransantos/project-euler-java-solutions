package reg.ru;

public class Task_9 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_9().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        for (int a = 1; a < 1000; ++a) {
            for (int b = a+1; b < 1000; ++b) {
                int c = 1000 - a - b;
                if (a >= b + c || b >= a + c || c >= a + b) {
                    continue;
                }
                if (a*a + b*b == c*c) {
                    System.out.println(a*b*c);
                }
            }
        }
    }
}
