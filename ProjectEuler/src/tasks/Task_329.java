package tasks;

import utils.log.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static utils.MyMath.setBit;

//Answer :
public class Task_329 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_329());
        Logger.close();
    }

    Set<Integer> primes = new HashSet<Integer>(
            Arrays.asList(
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                    173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359,
                    367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499));

    private double prob[] = new double[1 << 15];

    public void solving() {
        for (int i = 1; i <= 500; ++i) {
            System.out.println(i);
            find(i, 1.0/500.0, 0, 0);
        }

        System.out.println(prob[parseInt("PPPPNNPPPNPPNPN".replace('P', '1').replace('N', '0'), 2)]);
    }

    private void find(int n, double currp, int curr, int cnt) {
        if (cnt == 15) {
            prob[curr] += currp;
            return;
        }

        double pProb = isPrime(n) ? 2.0 / 3.0 : 1.0 / 3.0;
        double nProb = isPrime(n) ? 1.0 / 3.0 : 2.0 / 3.0;

        if (n == 1) {
            find(n + 1, currp * pProb, setBit(curr, cnt), cnt + 1);
            find(n + 1, currp * nProb, curr, cnt + 1);
        } else if (n == 500) {
            find(n - 1, currp * pProb, setBit(curr, cnt), cnt + 1);
            find(n - 1, currp * nProb, curr, cnt + 1);
        } else {
            find(n + 1, currp * pProb / 2, setBit(curr, cnt), cnt + 1);
            find(n + 1, currp * nProb / 2, curr, cnt + 1);
            find(n - 1, currp * pProb / 2, setBit(curr, cnt), cnt + 1);
            find(n - 1, currp * nProb / 2, curr, cnt + 1);
        }
    }

    private boolean isPrime(int n) {
        return primes.contains(n);
    }
}
