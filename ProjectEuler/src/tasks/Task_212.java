package tasks;

//Answer :
public class Task_212 implements ITask {
    int s[] = new int[300000];

    public void solving() {
        for (int k = 1; k <= 55; ++k) {
            s[k - 1] = (100003 - 200003 * k + ((((300007 * k)% 1000000) * k)% 1000000) * k) % 1000000 - 500000;
        }

        for (int k = 55; k < 300000; ++k) {
            s[k] = (s[k - 24] + s[k - 55] + 1000000) % 1000000 - 500000;
        }



    }

    public static void main(String[] args) {
        Tester.test(new Task_212());
    }
}