package solved;

import tasks.ITask;

import static utils.MyMath.getPrimesBetween;

//Answer : 1097343
public class Task_087 implements ITask {
    private static final int MAX = 50000000;
    boolean can[] = new boolean[MAX+1];

    public void solving() {
        long res = 0;
        long p[] = getPrimesBetween(0, (long) Math.sqrt(MAX)+5);
        int n = p.length;

        for (int i = 0; i < n; ++i) {
            long p1 = (long) Math.pow(p[i], 4);
            for (int j = 0; j < n; ++j) {
                long p2 = (long) Math.pow(p[j], 3);
                if (p1+p2 > MAX) break;

                for (int k = 0; k < n; ++k) {
                    long p3 = (long) Math.pow(p[k], 2);
                    int num = (int) (p1+p2+p3);
                    if (num > MAX) break;

                    if (!can[num]) {
                        ++res;
                        can[num] = true;
                    }
                }
            }
        }

        System.out.println( res );
    }
}
