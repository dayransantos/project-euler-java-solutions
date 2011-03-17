package solved;

import tasks.ITask;

import static utils.MyMath.getPrimeDivisors;

//Answer : 134043
public class Task_047 implements ITask {
    final int NEED = 4;
    int curr = 0;

    public void solving() {
        int i = 1;
        for (;; ++i) {
            if ( isOk(i) ) {
                ++curr;
                if (curr == NEED) break;
            } else {
                curr = 0;
            }
        }

        System.out.println( i - 3 );
    }

    private boolean isOk(int n) {
        return getPrimeDivisors(n).size() == NEED;
    }
}
