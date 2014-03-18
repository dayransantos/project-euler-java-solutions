package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Answer :
public class Task_13 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_13());
        Logger.close();
    }


    String s = "1220021001202010211110000122220012222111";
    char ch[] = s.substring(0, 10).toCharArray();
    int n = ch.length;
    int ds[] = new int[n];
    PrintWriter out;

    public void solving() throws FileNotFoundException {
        String s2 = s.replace('2', '5');
        String s3 = new StringBuilder(s).reverse().toString();
        String s4 = s.replace('0', '3').replace('2', '0').replace('3', '2');
        System.out.println(s);

        for (int i = 0 ; i < n; ++i) {
            ds[i] = ch[i] - '0';
        }

//        test("abracadabra");

        System.out.println(new String(ch));

        out = new PrintWriter("D:\\40.out");
        f[0] = achar(ds[0]);
        find(ds[0], 0, 1);
        out.close();
    }

    private void test(String os) {
        for (char ch : os.toCharArray()) {
            System.out.print(Integer.toString(ch - 'a', 10));
        }
        System.out.println();
    }

    char f[] = new char[n+3];

    private char achar(int k) {
        return (char)('a' + k);
    }

    private void find(int k, int currf, int ind) {
        //k is in f[currf]
        if (ind == n) {
            String os = new String(f, 0, currf + 1);
            test(os);
            out.println(os);
            return;
        }

        if (k < 0 || k >= 26) {
            throw new IllegalStateException("ouch");
        }

        int newk = k*10 + ds[ind];
//        if (ds[ind] != 0) {
        f[currf + 1] = achar(ds[ind]);
        find(ds[ind], currf + 1, ind + 1);
//        }
        if (newk < 26) {
            f[currf] = achar(newk);
            find(newk, currf, ind + 1);
        }
    }
}
