package tasks;

import utils.MyMath;
import utils.log.Logger;

import static utils.MyMath.getDivisors;

//Answer :
public class Task_157 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_157());
        Logger.close();
    }

    public void solving() {
//            a+b    p
//            --- = --
//             ab   10n
//
//             (a+b)*m = pk; b = pk/m - a;
//             ab = 10nk/m;
//
//             apk/m - a^2 = 10nk/m;
//
//             m*a^2 - pk*a + 10^n*k = 0;
//
//             D = p^2*k^2 - 4*m*k*10^n = (pk)^2 - k*m*4*10^n == B^2
//
//             a = (pk - sqrt(D))/(2*m)
//             b = (pk + sqrt(D))/(2*m)
//
//             (pk)^2 = B^2 + m*k*4*10^n;
//
//             (pk-B)(pk+B) = m*k*4*10^n = c*d;
//
//             c = s1 + s2;
//             d = s1 - s2;
//
//             s1 = pk = (c+d)/2
//             s2 = B  = (c-d)/2

        int cnt = 0;
        for (int s = 1; s < 100000; ++s) {
            long n = s*4*10;
            for (long d : getDivisors(n)) {
                long c = n / d;
                if (c < d) {
                    break;
                }

                if (d%2!=0 || c%2!=0) {
                    continue;
                }

                long pk = (c + d)/2;
                long B = (c-d)/2;

                for (long k : getDivisors(pk)) {
                    long p = pk/k;


                }
                if (pk%s==0 && pk%2==B%2) {
                    long p = pk / s;
                    long a = (pk - B)/2;
                    long b = (pk + B)/2;

                    System.out.println((++cnt) + ": a==" + a + "; b==" + b + "; p==" + p);
                }

                long p = pk;
                long a = p - B;
                long b = p + B;
                if (a%k2==0 && b%k2==0) {
                    a /= k2;
                    b /= k2;
                    System.out.println((++cnt) + ": a==" + a + "; b==" + b + "; p==" + p);
                }
            }
        }
     }
}
