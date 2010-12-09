import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
public class liarliar {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[] {"d:\\Work\\Stuff\\ProjectEuler\\facebook\\input.txt"};
        }
        new liarliar().run(args[0]);
    }

    private void run(String fileName) throws IOException {
        Map<String, Set<String>> link = new HashMap<String, Set<String>>();
        Scanner in = new Scanner(new File(fileName));
        int n = in.nextInt();
        if (n <= 0) {
            return;
        }

        for (int i = 0; i < n; ++i) {
            String name1 = in.next();
            int m = in.nextInt();

            for (int j = 0; j < m; ++j) {
                String name2 = in.next();

                getSet(link, name1).add(name2);
                getSet(link, name2).add(name1);
            }
        }

        class QEl {
            String name;
            boolean liar;
            public QEl(String name, boolean liar) {
                this.name = name;
                this.liar = liar;
            }
        }

        int a = 0;
        int b = 0;

        Set<String> used = new HashSet<String>();
        Queue<QEl> q = new LinkedList<QEl>();
        q.add(new QEl(link.keySet().iterator().next(), true));
        used.add(q.peek().name);
        while (!q.isEmpty()) {
            QEl el = q.poll();
            if (el.liar) {
                ++a;
            } else {
                ++b;
            }

            for (String child : link.get(el.name)) {
                if (!used.contains(child)) {
                    used.add(child);
                    q.add(new QEl(child, !el.liar));
                }
            }
        }

        System.out.println(Math.max(a, b) + " " + Math.min(a, b));
    }

    private Set<String> getSet(Map<String, Set<String>> link, String name) {
        Set<String> result = link.get(name);
        if (result == null) {
            result = new HashSet<String>();
            link.put(name, result);
        }
        return result;
    }
}
