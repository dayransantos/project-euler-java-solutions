package tasks;

import java.io.File;
import java.io.PrintStream;

import static utils.MyMath.doEratosthen;


public class PrimesGenerator {
    static boolean isPrime[] = new boolean[4733800];
    static long primes[] = new long[500000];
    static int primesCount = 0;

    public static void main(String[] args) {
        primesCount = doEratosthen(primes, isPrime);

        try {
            PrintStream outFile = new PrintStream(new File("./files/primes.dat"));
            for (int i = 0; i < primesCount; ++i) {
                outFile.println(primes[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
