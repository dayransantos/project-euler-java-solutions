package tasks;

import utils.log.Logger;

import static utils.MyMath.gcd;
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
//             ab*m = 10^n*k;
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
//             c = pk + B;
//             d = pk - B;
//
//             pk = (c+d)/2
//             B  = (c-d)/2

        int cnt = 0;
        for (int s = 1; s < 1000000; ++s) {
            long n = s * 4 * 1000;
            for (long d : getDivisors(n)) {
                long c = n / d;
                if (c < d) {
                    break;
                }

                if (d % 2 != 0 || c % 2 != 0) {
                    continue;
                }

                long pk = (c + d) / 2;
                long B = (c - d) / 2;

                for (long k : getDivisors(s)) {
                    if (pk%k!=0) {
                        continue;
                    }

                    long m = s/k;
                    if (gcd(m, k) != 1) {
                        continue;
                    }
                    long m2 = m*2;

                    long p = pk / k;
                    long a = (pk - B);
                    long b = pk + B;
                    if (a % m2 == 0 && b % m2 == 0) {
                        a /= m2;
                        b /= m2;
                        System.out.println((++cnt) + ": a==" + a + "; b==" + b + "; p==" + p);
                    }
                }
            }
        }
    }
}
