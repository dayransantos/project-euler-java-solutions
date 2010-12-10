import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class gattaca2 {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[] {"d:\\input.txt"};
        }
        new gattaca2().run(args[0]);
    }

    static class Gene implements Comparable<Gene>{
        boolean beg;
        int val;
        int score;
        int best = 0;

        Gene begin;
        
        public Gene(boolean beg, int val, int score) {
            this.beg = beg;
            this.val = val;
            this.score = score;
        }

        public int compareTo(Gene o) {
            int res =  compareInts(val, o.val);
            if (res != 0) return res;

            if (beg == o.beg) {
                return 0;
            }

            return beg ? -1 : 1;
        }

        @Override
        public String toString() {
            if (beg) {
                return "beg: " + val + "; score: " + score + "; best: " + best;
            } else {
                return "end: " + val + "; score: " + score + "; best: " + best + "(" + begin.toString() + ")";
            }
        }

        public int compareInts(int a, int b) {
            return (a<b ? -1 : (a==b ? 0 : 1));
        }
    }
    
    private void run(String fileName) throws IOException {
        Scanner in = new Scanner(new File(fileName));
        int n = in.nextInt();

        for (int i = 0; i < (n%80==0?n/80:n/80+1); ++i) {
            in.next();
        }

        int k = in.nextInt();
        n = 2*k;
        Gene g[] = new Gene[n];
        for (int i = 0; i < k; ++i) {
            int beg = in.nextInt();
            int end = in.nextInt();
            int score = in.nextInt();
            g[2*i] = new Gene(true, beg, score);
            g[2*i + 1] = new Gene(false, end, score);
            g[2*i + 1].begin = g[2*i];
        }

        Arrays.sort(g);

        g[0].best = 0;
        for (int i = 1; i < n; ++i) {
            g[i].best = g[i-1].best;
            if (!g[i].beg) {
                int bv = g[i].begin.best;
                g[i].best = Math.max(g[i].best, bv + g[i].score);
                if (!g[i-1].beg && g[i-1].val == g[i].val) {
                    g[i].best = Math.max(g[i].best, g[i-1].best);
                };
            }
        }

        System.out.println(g[n-1].best);
    }
}
