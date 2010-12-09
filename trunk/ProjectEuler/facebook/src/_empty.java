import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class _empty {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[] {"d:\\Work\\Stuff\\ProjectEuler\\facebook\\input.txt"};
        }
        new _empty().run(args[0]);
    }

    private void run(String fileName) throws IOException {
        Scanner in = new Scanner(new File(fileName));
        int n = in.nextInt();
    }
}
