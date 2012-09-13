package solved;

import tasks.ITask;
import tasks.Tester;
import utils.FractionPoint;
import utils.LongFraction;
import utils.Point;

import java.util.HashSet;

//Answer : 2868868
public class Task_165 implements ITask {
    int n = 5000;
    long s[] = new long[4 * n + 1];
    int t[] = new int[4 * n];

    public void solving() {
        s[0] = 290797;
        for (int i = 1; i < s.length; ++i) {
            s[i] = (s[i - 1] * s[i - 1]) % 50515093L;
            t[i-1] = (int) (s[i] % 500);
        }


        HashSet<FractionPoint> all = new HashSet<FractionPoint>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                FractionPoint p = getTrueIntersection(
                        t[4*i], t[4*i+1], t[4*i+2], t[4*i+3],
                        t[4*j], t[4*j+1], t[4*j+2], t[4*j+3]);

                if (p != null) {
                    all.add(p);
                }
            }
        }

        System.out.println(all.size());

    }

    private FractionPoint getTrueIntersection(int x1, int y1, int x2, int y2,
                                       int x3, int y3, int x4, int y4) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);

        return getTrueIntersection(p1, p2, p3, p4);
    }

    public long twistedProduct(Point a1, Point a2, Point b1, Point b2) {
        return (a2.x - a1.x)*(b2.y - b1.y) - (a2.y - a1.y)*(b2.x - b1.x);
    }

    public FractionPoint getTrueIntersection(Point p1, Point p2, Point p3, Point p4) {
        long z1 = twistedProduct(p1, p2, p1, p3);
        long z2 = twistedProduct(p1, p2, p1, p4);

        long z3 = twistedProduct(p3, p4, p3, p1);
        long z4 = twistedProduct(p3, p4, p3, p2);

        if (z1*z2 >= 0 || z3*z4 >= 0) return null;

// p1M = p1p2 * [p3p4, p3p1] / [p1p2, p3p4];

        long z = twistedProduct(p1, p2, p3, p4);
        LongFraction mx = new LongFraction(p1.x*z + (p2.x - p1.x) * z3, z);
        LongFraction my = new LongFraction(p1.y*z + (p2.y - p1.y) * z3, z);

        return new FractionPoint(mx, my);
    }

    public static void main(String[] args) {
        Tester.test(new Task_165());
    }
}