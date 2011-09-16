package utils;

import org.apfloat.ApintMath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.*;
import static java.math.BigInteger.valueOf;

public class MyMath {
    public static long pow10[] = {
        1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,
        100000000, 1000000000, 10000000000L, 100000000000L
    };

    private static ArrayList<Long> cachedPrimes;

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        a = abs(a);
        b = abs(b);

        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        a = abs(a);
        b = abs(b);

        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(gcd(a, b)).multiply(b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static boolean isPrime(long n) {
        initPrimesList();
        if (n <= cachedPrimes.get(cachedPrimes.size() - 1)) {
            return Collections.binarySearch(cachedPrimes, n) >= 0;
        }

        return isPrime(getCachedPrimes(), cachedPrimes.size(), n);
    }

    public static int doEratosthen(long primes[], int range) {
        return doEratosthen(primes, new boolean[range]);
    }

    /**
     *
     * @param primes - outArray for caching primes, null if no need caching
     * @param outArray - outArray to use for sieve
     * @return numer of cached primes
     */
    public static int doEratosthen(long primes[], boolean[] outArray) {
        int primesCount = 0;
        Arrays.fill(outArray, true);

        outArray[0] = outArray[1] = false;

        int n = outArray.length - 1;
        for (long i = 2; i <= n; ++i) {
            if (outArray[(int) i]) {
                if (primes != null) {
                    primes[primesCount++] = i;
                    if (primesCount == primes.length) {
                        return primesCount;
                    }
                }

                for (long j = i * i; j <= n; j += i) {
                    outArray[(int) j] = false;
                }
            }
        }

        return primesCount;
    }

    public static boolean isPrime(long cachedPrimes[], int primesCount, long n) {
        if (n == 2) {
            return true;
        }

        int i;
        for (i = 0; i < primesCount && cachedPrimes[i] * cachedPrimes[i] <= n; ++i) {
            if (n % cachedPrimes[i] == 0) {
                return false;
            }
        }

        if (i == primesCount) {
            for (long j = cachedPrimes[primesCount - 1]; j * j <= n; j += 2) {
                if (n % j == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static long getProperDivisorsSum(long n) {
        return getDivisorsSum(n) - n;
    }

    public static long getDivisorsSum(long n) {
        long[] ps = getPrimesBetween(0, (long)(Math.sqrt(n) + 2));
        long sum = 1;
        long m = n;
        for (long p : ps) {
            long dp = 1;
            while (m % p == 0) {
                m /= p;
                dp = dp * p + 1;
            }
            sum *= dp;
        }

        //some prime's left
        if (m > 1) {
            sum *= (m + 1);
        }
        return sum;
    }

    public static long getDivisorsCount(long n) {
        if (n == 1) {
            return 1;
        }
        long res = 2; // 1 and n
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                long j = n / i;
                res += (i == j) ? 1 : 2;
            }
        }

        return res;
    }

    public static ArrayList<Long> getDivisors(long n) {
        ArrayList<Long> all = new ArrayList<Long>();

        all.add(1L);

        if (n == 1) {
            return all;
        }

        all.add(n);
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                long j = n / i;

                all.add(i);
                if (j != i) {
                    all.add(j);
                }
            }
        }

        Collections.sort(all);

        return all;
    }

    public static ArrayList<Long> getPrimeDivisors(long n) {
        initPrimesList();
        ArrayList<Long> all = new ArrayList<Long>();

        for (int i = 0; i < cachedPrimes.size(); ++i) {
            long p = cachedPrimes.get(i);

            if (n % p == 0) {
                all.add(p);
                while (n % p == 0) {
                    n /= p;
                }
                if (n == 1) {
                    return all;
                }
            }

            if (p * p >= n) {
                if (isPrime(n)) {
                    all.add(n);
                }
                return all;
            }
        }

        long last = cachedPrimes.get(cachedPrimes.size() - 1);
        assert ((last & 1) != 0);

        for (long i = last + 2; i * i <= n; i += 2) {
            if (n % i == 0 && isPrime(i)) {
                all.add(i);
                while (n % i == 0) {
                    n /= i;
                }
                if (n == 1) {
                    return all;
                }
            }
        }

        if (isPrime(n)) {
            all.add(n);
        }

        // not needed
//        Collections.sort(all);

        return all;
    }
    private final static int MAX_PRIMES_TO_CACHE = 400000;

    private static void initPrimesList() {
        if (cachedPrimes != null) {
            return;
        }

        cachedPrimes = new ArrayList<Long>(MAX_PRIMES_TO_CACHE);
        try {
            BufferedReader inFile = new BufferedReader(new FileReader("./files/primes.dat"));

            String line = inFile.readLine();
            while (line != null && cachedPrimes.size() < MAX_PRIMES_TO_CACHE) {
                cachedPrimes.add(Long.parseLong(line));
                line = inFile.readLine();
            }
        } catch (FileNotFoundException e) {
            cachedPrimes.add(2L);
            cachedPrimes.add(3L);
        } catch (IOException e) {
        }
    }

    public static long[] getCachedPrimes() {
        initPrimesList();

        int n = cachedPrimes.size();
        long[] primes = new long[n];
        for (int i = 0; i < n; ++i) {
            primes[i] = cachedPrimes.get(i);
        }

        return primes;
    }

    private static long cachedPrimesArr[];
    public static long[] getCachedPrimesInternal() {
        if (cachedPrimesArr != null) {
            return cachedPrimesArr;
        }
        initPrimesList();

        int n = cachedPrimes.size();
        cachedPrimesArr = new long[n];
        for (int i = 0; i < n; ++i) {
            cachedPrimesArr[i] = cachedPrimes.get(i);
        }

        return cachedPrimesArr;
    }


    public static long[] getPrimesBetween(long beg, long end) {
        initPrimesList();

        long lastPrime = cachedPrimes.get(cachedPrimes.size() - 1);
        long n = lastPrime + 2;
        for (; n <= end; n += 2) {
            if (isPrime(n)) {
                cachedPrimes.add(n);
            }
        }

        return getCachedPrimesBetween(beg, end);
    }

    private static long[] getCachedPrimesBetween(long beg, long end) {
        initPrimesList();

        int b = 0;
        while (cachedPrimes.get(b) < beg) {
            b++;
        }
        int e = b;
        while (cachedPrimes.get(e) < end) {
            e++;
        }
        --e;

        int cnt = e - b + 1;
        long ret[] = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            ret[i] = cachedPrimes.get(b + i);
        }
        return ret;
    }

    public static boolean hasCommonDivisors(long a, long b) {
        long g = gcd(a, b);
        return g > 1;
    }
//    Fuck... how could I write this!..
//    public static boolean hasCommonDivisors(long a, long b) {
//        ArrayList<Long> divs1 = getDivisors(a);
//        divs1.remove(0);
//        ArrayList<Long> divs2 = getDivisors(b);
//
//        for (long div : divs1) {
//            if (divs2.contains(div)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
    public static boolean isExactSquare(long n) {
        long sqrt = (long) Math.sqrt(n);
        return (sqrt * sqrt == n);
    }

    /**
     * @deprecated use ApintMath.sqrt instead
     * @param n
     * @return
     * @see ApintMath#sqrt(org.apfloat.Apint) 
     */
    public static BigInteger sqrt(final BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return n;
        }

        // use a half-decent initial approximation
        BigInteger x_new = BigInteger.ONE.shiftLeft((n.bitLength() + 1) / 2);
        BigInteger x_old;

        // finish off with Newton-Raphson: x_new = (n/x_old + x_old)/2
        do {
            x_old = x_new;
            x_new = n.divide(x_old).add(x_old).shiftRight(1);
        } while (x_old.compareTo(x_new) > 0);

        return x_new;
    }

    public static boolean isExactSquare(BigInteger n) {

        BigInteger sqrt = sqrt(n);
        return (sqrt.multiply(sqrt).equals(n));
    }

    /**
     * Euler's totient function
     */
    public static long phi(long n) {
        long res = n;
        ArrayList<Long> divs = getPrimeDivisors(n);
        for (long div : divs) {
            res = (res - res / div);
        }

        return res;
    }

    public static boolean isPowerOfTwo(long x) {
        return (x != 0) && ((x & (x - 1)) == 0);
    }

    public static long pow(long n, int p) {
        if (p==1) return n;
        if (p==2) return n*n;
        if (p==3) return n*n*n;

        long r = 1;
        for (int i = 0; i < p; ++i) {
            r *= n;
        }
        return r;
    }

    public static boolean isBitSet(int n, int bInd) {
        return (n & (1<<bInd)) != 0;
    }

    public static int setBit(int n, int bInd) {
        return n | (1<<bInd);
    }

    public static int unSetBit(int n, int bInd) {
        return n & (~(1<<bInd));
    }

    public static boolean isBitSet(long n, int bInd) {
        return (n & (1<<bInd)) != 0;
    }

    public static long setBit(long n, int bInd) {
        return n | (1<<bInd);
    }

    public static long unSetBit(long n, int bInd) {
        return n & (~(1<<bInd));
    }

    public static int sumOfDigits(long n) {
        int sum = 0;
        while (n != 0) {
            sum += n%10;
            n /= 10;
        }

        return sum;
    }

    public static int numberOfDigits(long n) {
        return (int)floor( log10( abs( n != 0 ? n : 1 ) ) ) + 1;
    }

    public static boolean isProbablePrime(long n, int certainty) {
        return valueOf(n).isProbablePrime(certainty);
    }

    public static BigInteger bi(long n) {
        return valueOf(n);
    }
}
