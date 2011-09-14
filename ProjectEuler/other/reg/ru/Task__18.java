package reg.ru;

public class Task__18 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__18().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    String s = "75\n" +
               "95 64\n" +
               "17 47 82\n" +
               "18 35 87 10\n" +
               "20 04 82 47 65\n" +
               "19 01 23 75 03 34\n" +
               "88 02 77 73 07 63 67\n" +
               "99 65 04 28 06 16 70 92\n" +
               "41 41 26 56 83 40 80 70 33\n" +
               "41 48 72 33 47 32 37 16 94 29\n" +
               "53 71 44 65 25 43 91 52 97 51 14\n" +
               "70 11 33 28 77 73 17 78 39 68 17 57\n" +
               "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
               "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
               "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

    int k = 15;

    private String[] spl;

    public void solving() {
        spl = s.split(" |\n");

        long n[][] = new long[k][k];
        long mx = n[0][0] = get(0, 0);

        for (int r = 1; r < k; ++r) {
            for (int c = 0; c <= r; ++c) {
                long ds = n[r-1][c];
                if (c > 0) {
                    ds = Math.max(ds, n[r-1][c-1]);

                }

                n[r][c] = get(r, c) + ds;

                mx = Math.max(mx, n[r][c]);
            }
        }
        System.out.println(mx);
    }

    private long get(int r, int c) {
        return Integer.parseInt( spl[r*(r+1)/2 + c] );
    }
}
