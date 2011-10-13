package tasks;

import utils.log.Logger;

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

     }
}
