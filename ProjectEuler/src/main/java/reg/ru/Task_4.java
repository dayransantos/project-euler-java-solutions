package reg.ru;

public class Task_4 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task_4().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        long res = 0;
        for (int i = 100; i < 999; ++i) {
            for (int j = i+1; j <=999; ++j) {
                if (isPalindrom(i*j)) {
                    res = Math.max(res, i*j);
                }
            }
        }
        System.out.println(res);
    }

    private boolean isPalindrom(int n) {
        String sn = "" + n;
        return sn.equals(new StringBuffer(sn).reverse().toString());
    }
}
