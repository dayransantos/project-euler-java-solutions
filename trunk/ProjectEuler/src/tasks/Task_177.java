package tasks;

import utils.log.Logger;

import java.util.*;

import static java.lang.Math.*;

//Answer : 129325
public class Task_177 implements ITask {
    private static final double EPS = 1e-7;

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_177());
        Logger.close();
    }

    public double sin[] = new double[181];
    public double cos[] = new double[181];

    public void solving() {
        for (int i = 0; i <= 180; ++i) {
            sin[i] = Math.sin(i * PI / 180);
            cos[i] = Math.cos(i * PI / 180);
        }

        for (int a = 1; a < 180; ++a) {
            double sina = sin[a];
            for (int b = 1; a + b < 180; ++b) {
                int i = 180 - a - b;

                double sinb = sin[b];
                double sinc1 = sin[i];
                double cosc1 = cos[i];
                for (int c = 1; a + b + c < 180; ++c) {
                    int d = 180 - a - b - c;
                    double sinc = sin[c];
                    double sind = sin[d];
                    for (int h = 1; a + b + h < 180; ++h) {
                        int g = 180 - a - b - h;

                        double sinh = sin[h];
                        double sing = sin[g];

                        double oa = sinb / sinc1;
                        double od = oa * sinh / sing;

                        double ob = sina / sinc1;
                        double oc = ob * sinc / sind;

                        double dc = sqrt(od * od + oc * oc - 2 * oc * od * cosc1);

                        double ed = acos((oc * oc + dc * dc - od * od) / 2 * dc * oc) * 180 / PI;

                        int e = (int) round(ed);
                        if (e == 0 || e == 180 || abs(e - ed) > EPS) {
                            continue;
                        }

                        if (d + e < 180 && e + i < 180 && a + b + c + d + e + h < 360) {
                            addRect(a, b, c, d, e, 180 - e - i, g, h);
                        }
                    }
                }
            }
        }
        System.out.println(all2.size());
    }

    Set<List<Integer>> all2 = new HashSet<List<Integer>>();

    private void addRect(int a, int b, int c, int d, int e, int f, int g, int h) {
        List<Integer> key1, key2, key3, key4, key5, key6, key7, key8;
        key1 = Arrays.asList(a, b, c, d, e, f, g, h);
        key2 = Arrays.asList(c, d, e, f, g, h, a, b);
        key3 = Arrays.asList(e, f, g, h, a, b, c, d);
        key4 = Arrays.asList(g, h, a, b, c, d, e, f);
        key5 = new ArrayList<Integer>(key1);
        key6 = new ArrayList<Integer>(key2);
        key7 = new ArrayList<Integer>(key3);
        key8 = new ArrayList<Integer>(key4);
        Collections.reverse(key5);
        Collections.reverse(key6);
        Collections.reverse(key7);
        Collections.reverse(key8);

        List<Integer> key = Collections.max(Arrays.asList(key1, key2, key3, key4, key5, key6, key7, key8), new Comparator<List<Integer>>() {
            public int compare(List<Integer> k1, List<Integer> k2) {
                for (int i = 0; i < k1.size(); ++i) {
                    int o1 = k1.get(i);
                    int o2 = k2.get(i);
                    if (o1 > o2) return 1;
                    if (o2 > o1) return -1;
                }
                return 0;
            }
        });

        all2.add(key);
//        if (all2.contains(key)) {
//            return;
//        }
//
//        all2.add(key1);
//        all2.add(key2);
//        all2.add(key3);
//        all2.add(key4);
//        all2.add(key5);
//        all2.add(key6);
//        all2.add(key7);
//        all2.add(key8);
//        ++cnt;
    }
}
