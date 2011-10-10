package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_157 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_157());
        Logger.close();
    }

    public void solving() {
//        1.
//            a+b    p
//            --- = --
//             ab   10n
//
//             a+b = pk; b = pk - a;
//             ab = 10nk;
//
//             apk - a^2 = 10nk;
//
//             a^2 - pk*a + 10^n*k = 0;
//
//             D = p^2*k^2 - 4k*10^n = (pk)^2 - k*4*10^n == B^2
//
//                 a = (pk - sqrt(D))/2
//                 b = (pk + sqrt(D))/2
//
//             (pk)^2 = B^2 + k*4*10^n;
//
//             (pk-B)(pk+B) = k*4*10^n = c*d;
//
//             c = s1 + s2;
//             d = s1 - s2;
//
//             s1 = pk = (c+d)/2
//             s2 = B  = (c-d)/2
//
//
//        2.
//            a+b    p
//            --- = --
//             ab   10n
//
//             (a+b)*k = p; b = p/k - a;
//             ab = 10n/k;
//
//             ap/k - a^2 = 10n/k;
//             ap - k*a^2=10^n
//             k*a^2 - p*a + 10^n = 0;
//
//             D = p^2 - 4k*10^n = (p)^2 - k*4*10^n == B^2
//
//                 a = (p - sqrt(D))/(2k)
//                 b = (p + sqrt(D))/(2k)
//
//             (p)^2 = B^2 + k*4*10^n;
//
//             (p-B)(p+B) = k*4*10^n = c*d;
//
//             c = s1 + s2;
//             d = s1 - s2;
//
//             s1 = p = (c+d)/2
//             s2 = B = (c-d)/2


        int cnt = 0;
        for (int k = 1; k < 100000; ++k) {
            long k2 = k*2;
            long n = k*4*10;
            for (long d : MyMath.getDivisors(n)) {
                long c = n / d;
                if (c < d) {
                    break;
                }

                if (d%2!=0 || c%2!=0) {
                    continue;
                }

                long pk = (c + d)/2;
                long B = (c-d)/2;
                if (pk%k==0 && pk%2==B%2) {
                    long p = pk / k;
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
