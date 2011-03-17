package solved;

import tasks.ITask;

//Answer : 20492570929
public class Task_116 implements ITask {
    final int SZ = 50;
    public void solving() {
        System.out.println( count(2) + count(3) + count(4) );
    }

    private long count(int n) {
        long[] d = new long[SZ+1];
        for (int i = 0; i < n; ++i) {
            d[i] = 1;
        }

        for (int i = n; i <= SZ; ++i) {
            d[i] = d[i-1] + d[i-n];
        }
        return d[SZ]-1;
    }
}
