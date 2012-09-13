package tasks;

import utils.MyMath;
import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer :
//http://en.wikipedia.org/wiki/RSA_%28algorithm%29#cite_note-4
public class Task_182 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_182());
        Logger.close();
    }

    long p = 1009;
    long q = 3643;
    long n = p*q;
    long phi = (p-1)*(q-1);

    long p1 = p - 1;
    long q1 = q - 1;

    long qinv = MyMath.modInverse(p, q);

    public void solving() {
        System.out.println(n);
        for (long m = 0; m < n; ++m) {
            if (m % 1000 == 0) {
                System.out.println("Progress: " + m);
            }
            long p = m;
            while (true) {
                p = (p*m)%n;
                if (p == m) {
                    break;
                }
            }
        }

        long res = 0;
        long min = 9;
        E: for (long e = 2; e < phi; ++e) {
            if (e % 100000 == 0) {
                System.out.println("Progress: " + e);
            }
            if (gcd(e, phi) == 1) {
                long ep = e % p1;
                long eq = e % q1;

                int cnt = 0;
                for (long m = 0; m < n; ++m) {
//                    long c = MyMath.modPow(m, e, n);
                    long c = modPowPQ(m, e, ep, eq);
                    if (m == c) {
                        ++cnt;
                        if (cnt > min) {
                            continue E;
                        }
                    }
                }

                if (cnt < min) {
                    res = e;
                    min = cnt;
                } else {
                    // == min
                    res += e;
                    System.out.println(e + " " + res);
                }
            }
        }
        System.out.println(res);
    }

    public long modPowPQ(long m, long e, long ep, long eq) {
        long mp = MyMath.modPow(m, ep, p);
        long mq = MyMath.modPow(m, eq, q);
        long h = (qinv*(mp + p - mq))%p;
        return mq + h * q;
    }
}
