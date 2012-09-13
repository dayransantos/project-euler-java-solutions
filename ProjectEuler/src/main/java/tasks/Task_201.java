package tasks;

import utils.STLUtils;
import utils.log.Logger;
import utils.triples.IntTriple;

import java.util.HashMap;

//Answer :
public class Task_201 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_201());
        Logger.close();
    }

    HashMap<IntTriple, Integer> cnt = new HashMap<IntTriple, Integer>();

    int n = 100;
    int need = 50;
    int set[] = new int[n];
    int maxsum[][] = new int[n][n + 1];
    int mx;

    public void solving() {
        for (int i = 0; i < n; ++i) {
            set[i] = (i + 1) * (i + 1);
        }


//        set = new int[]{1, 3, 6, 8, 10, 11};
//        need = 3;
//        n = set.length;


        int sum[] = new int[n];
        sum[0] = set[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + set[i];
        }

        for (int i = 0; i < n; ++i) {
            for (int left = 1; left <= Math.min(i+1, need); ++left) {
                if (left > i) {
                    maxsum[i][left] = sum[i];
                } else {
                    maxsum[i][left] = sum[i] - sum[i - left];
                }
            }
        }

        mx = STLUtils.accumulate(set);

        long res = 0;
        for (int i = 0; i <= mx; ++i) {
            if (i % 10000 == 0) {
                System.out.println(i);
            }
            int c = getResult(i);
            if (c == 1) {
                System.out.println("Got: " + i);
                res += (long) i;
            }
        }

        System.out.println(res);
    }

    private int getResult(int current) {
        if (current < maxsum[need-1][need]) {
            return 0;
        }

        int c = 0;
        for (int last = need - 1; last < n; ++last) {
            if (maxsum[last][need] < current) {
                continue;
            }

            c += getNumberOfSets(current - set[last], last - 1, 1);
        }
        return c;
    }

    private int getNumberOfSets(int current, int maxInd, int usedCnt) {
        if (usedCnt == need) {
            return current == 0 ? 1 : 0;
        }

        if (current > maxsum[maxInd][need - usedCnt]) {
            return 0;
        }

        IntTriple t = new IntTriple(current, maxInd, usedCnt);
        if (cnt.containsKey(t)) {
            return cnt.get(t);
        }

        int c = 0;
        for (int i = need-usedCnt-1; i <= maxInd && current >= set[i]; ++i) {
            if (maxsum[i][need - usedCnt] < current) {
                continue;
            }

            c += getNumberOfSets(current - set[i], i - 1, usedCnt + 1);
        }

        if (c > 2) {
            c = 2;
        }

        cnt.put(t, c);

        return c;
    }
}
