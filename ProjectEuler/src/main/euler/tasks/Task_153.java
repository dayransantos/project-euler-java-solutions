package tasks;

import utils.MyMath;
import utils.log.Logger;

import static utils.MyMath.decomposePrimeAsToSquares;
import static utils.MyMath.getPrimesBetween;

//Answer :
public class Task_153 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_153());
        Logger.close();
    }


    class Complex {
        long a;
        long b;

        Complex(long a, long b) {
            this.a = a;
            this.b = b;
        }

        public Complex mult(long mult) {
            return new Complex(a*mult, b*mult);
        }

        public Complex mult(Complex c) {
            return new Complex(a*c.a - b*c.b, a*c.b + b*c.a);
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
        }
    }

    Complex ONE = new Complex(1, 0);

//    long LIM = 100000000;
//    long LIM = 100000;
    long LIM = 5;

    long primes[];
    int pn;

    Complex add[];
    Complex sub[];
    Complex addpows[][];
    int addscnt[];
    Complex subpows[][];
    int subscnt[];


    public void solving() {
        MyMath.setMaxPrimesToCache(1500);

        primes = getPrimesBetween(0, 10000);
        pn = primes.length;

        add = new Complex[pn];
        sub = new Complex[pn];
        addpows = new Complex[pn][30];
        addscnt = new int[pn];
        subpows = new Complex[pn][30];
        subscnt = new int[pn];

        for (int i = 0; i < pn; ++i) {
            addpows[i][0] = ONE;
            subpows[i][0] = ONE;
            addscnt[i] = 0;
            subscnt[i] = 0;
        }

        for (int i = 0; i < primes.length; i++) {
            long p = primes[i];
            if (p == 2) {
                add[0] = new Complex(1, 1);
                sub[0] = new Complex(1, -1);
            } else if (p%4 == 1) {
                long ab[] = decomposePrimeAsToSquares(primes[i]);

                add[i] = new Complex(ab[0], ab[1]);
                sub[i] = new Complex(ab[0], -ab[1]);
            }
        }

        long res = 1; //1 for 1

        int p2;
        int p1ind[] = new int[20];
        int p1c[] = new int[20];
        int p1n;

        for (long i = 2; i <= LIM; ++i) {
            long k = i;
            boolean dicomposable = true;
            long divsum = 1;
            long p3mult = 1;
            p1n = 0;

            p2 = 0;
            while ((k&1) == 0) {
                p2++;
                k >>= 1;
                divsum = divsum*2 + 1;
            }

            for (int j = 1; j < primes.length; j++) {
                long p = primes[j];
                if (p*p > k) {
                    p = k;
                }

                if (k == 1) {
                    break;
                }

                if (k%p == 0) {
                    int cnt = 1;
                    long dp = p + 1;
                    long dp2 = 1;
                    k /= p;
                    while (k%p == 0) {
                        ++cnt;
                        k /= p;
                        dp = dp*p + 1;
                        if ((cnt&1) == 0) {
                            dp2 *= p;
                        }
                    }

                    divsum *= dp;

                    if (dicomposable) {
                        if (p%4 == 3) {
                            if ((cnt&1)!=0) {
                                dicomposable = false;
                                continue;
                            }

                            p3mult *= dp2;
                        } else {
                            p1ind[p1n] = j;
                            p1c[p1n++] = cnt;
                        }

                        dicomposable = false;
                    }
                }
            }

            if (dicomposable) {
                res += findComplexDivsSum(p2, p3mult, p1ind, p1c, p1n);
            }

            res += divsum;
        }
        System.out.println(res);
    }

    private long findComplexDivsSum(int p2, long p3mult, int[] p1ind, int[] p1c, int p1n) {
        Complex n23 = getAddPow(0, p2).mult(p3mult);

        for (int i = 0; i < p1n; ++i) {
            if ((p1c[i] & 1) != 0) {
                int t = p1ind[i];
                p1ind[i] = p1ind[0];
                p1ind[0] = t;

                t = p1c[i];
                p1c[i] = p1c[0];
                p1c[0] = t;
                break;
            }
        }

        if ((p1ind[0]&1) != 0) {
            return 2*hasOddInFactorization(p1ind, p1c, p1n, n23, 0, true);
        } else {
            return 2*allEvenFactorization(p1ind, p1c, p1n, n23, 0, true);
        }
    }

    private long hasOddInFactorization(int[] p1ind, int[] p1c, int p1n, Complex current, int ind, boolean half) {
        if (ind == p1n) {
            return current.a == current.b ? current.a : current.a + current.b;
        }
        long res = 0;
        int ei = half ? p1c[ind]/2 : p1c[ind];
        for (int i = 0; i <= ei; ++i) {
            res += hasOddInFactorization(p1ind, p1c, p1n,
                                         current.mult(getAddPow(p1ind[ind], i))
                                                 .mult(getSubPow(p1ind[ind], p1c[ind] - i)),
                                         ind + 1, false);
        }
        return res;
    }

    private long allEvenFactorization(int[] p1ind, int[] p1c, int p1n, Complex current, int ind, boolean half) {
        if (ind == p1n) {
            return current.a == current.b ? current.a : current.a + current.b;
        }

        long res = 0;
        if (half) {
            int ei = p1c[ind]/2;
            for (int i = 0; i <= ei; ++i) {
                res += allEvenFactorization(p1ind, p1c, p1n,
                                             current.mult(getAddPow(p1ind[ind], i))
                                                     .mult(getSubPow(p1ind[ind], p1c[ind] - i)),
                                             ind + 1, i == ei);
            }
        } else {
            for (int i = 0; i <= p1c[ind]; ++i) {
                res += allEvenFactorization(p1ind, p1c, p1n,
                                             current.mult(getAddPow(p1ind[ind], i))
                                                     .mult(getSubPow(p1ind[ind], p1c[ind] - i)),
                                             ind + 1, false);
            }
        }
        return res;
    }

    private Complex getSubPow(int ind, int p) {
        if (p == 0) {
            return ONE;
        }

        int cnt = subscnt[ind];
        if (p > cnt) {
            while (cnt < p) {
                subpows[ind][++cnt] = subpows[ind][cnt - 1].mult(sub[ind]);
            }
        }
        return subpows[ind][p];
    }

    private Complex getAddPow(int ind, int p) {
        if (p == 0) {
            return ONE;
        }

        int cnt = addscnt[ind];
        if (p > cnt) {
            while (cnt < p) {
                addpows[ind][++cnt] = addpows[ind][cnt - 1].mult(add[ind]);
            }
        }
        return addpows[ind][p];
    }
}
