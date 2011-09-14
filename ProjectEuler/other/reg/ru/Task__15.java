package reg.ru;

public class Task__15 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__15().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public final int sz = 20;
    long c[][] = new long[sz+1][sz+1];

    public void solving() {
        c[0][0] = 1;
        for (int i = 0; i <= sz; ++i) {
            for (int j = 0; j <= sz; ++j) {
                if (i > 0) {
                    c[i][j] = c[i-1][j];
                }
                if (j > 0) {
                    c[i][j] += c[i][j-1];
                }
            }
        }

        System.out.println(c[sz][sz]);
    }
}
