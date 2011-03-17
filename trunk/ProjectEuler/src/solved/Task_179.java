package solved;

import tasks.ITask;

//Answer : 986262
public class Task_179 implements ITask {
    private static final int MAX = 10000000;

    int cnt[] = new int[MAX];

    public void solving() {
        long res = 0;

        cnt[1] = 1;
        for (int i = 2; i < MAX; i++) {
            cnt[i] += 2;
            if (cnt[i] == cnt[i - 1]) ++res;
            for (int j = i + i; j < MAX; j += i) {
                cnt[j]++;
            }
        }

        System.out.println(res);
    }
}