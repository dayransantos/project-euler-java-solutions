package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_464 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_464());
        Logger.close();
    }
    
    public int LIM = 20000000;
//    public int LIM = 10;

    int n;
    int factorCnt;
    
    int meb[] = new int[LIM + 1];
    int pos[] = new int[LIM + 1];
    int neg[] = new int[LIM + 1];
    int f1[] = new int[LIM + 1]; 
    int f2[] = new int[LIM + 1]; 

    public void solving() {
        MyMath.setMaxPrimesToCache(2000000);
        long[] primes = MyMath.getCachedPrimes();

        System.out.println("Go");
        int pn = 0;
        int nn = 0;
        for (n = 1; n <= LIM; ++n) {
            factorCnt = 0;
            long n2 = n;
            
            for (long p : primes) {
                if (n2 % p == 0) {
                    n2 /= p;
                    if (n2 % p == 0) {
                        factorCnt = -1;
                        break;
                    }
                    factorCnt++;
                }

                if (p * p >= n2) {
                    if (n2 != 1) {
                        factorCnt++;
                    }
                    break;
                }
            }

            meb[n] = factorCnt < 0
                     ? 0
                     : factorCnt % 2 == 0
                       ? 1
                       : -1;
            if (meb[n] > 0) {
                pn++;
                pos[n] = pos[n-1] + 1;
                neg[n] = neg[n-1];
            } else if (meb[n] < 0) {
                nn++;
                pos[n] = pos[n-1];
                neg[n] = neg[n-1] + 1;
            }
            f1[n] = 99*neg[n] - 100*pos[n];
            f2[n] = 99*pos[n] - 100*neg[n];
//            System.out.println(n + ": " + meb[n]);
        }
        
        System.out.println("pos: " + pn);
        System.out.println("neg: " + nn);


//        
//        99*(n(b) - n(a)) <= 100*(p(b) - p(a))
//        99*(p(b) - p(a)) <= 100*(n(b) - n(a))
//
//        99*n(b) - 100*p(b) <= 99*n(a) - 100*p(a)
//        99*p(b) - 100*n(b) <= 99*p(a) - 100*n(a)


    }
}
