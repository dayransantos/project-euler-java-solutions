package tasks;

//Answer :
public class Task_218_2 implements ITask {
    private long LIM =  10000000000000000L;
    private long LIM2 = 100000000;

    public void solving() {
        long res = 0;
        for (long c2 = 1; c2 <= LIM2; ++c2) {
            long c = c2*c2;



        }
//
//
//        for (long c : perfect) {
//            if (c > LIM) break;
//            long maxm = (long) (sqrt(c / 2) + 2);
//            for (int m = 2; m <= maxm;++m) {
//                long n2 = c - m*m;
//                long n = (long) sqrt(n2);
//                if (m%2==n%2 || n*n != n2 || gcd(m,n)!=1) continue;
//
//                long a = 2*m*n;
//                long b = m*m - n*n;
//
//                if (!divedesPerfect(a/2, b)) {
//                    ++res;
//                } else {
//                    System.out.println("Isn't: " + a + " " + b + " " + c);
//                }
//            }
//        }

        System.out.println(res);

    }

    public static void main(String[] args) {
        Tester.test(new Task_218_2());
    }
}
