package math14;

import tasks.ITask;
import tasks.Tester;
import utils.Point;
import utils.log.Logger;

import static utils.Geometry.intersects;
import static utils.Geometry.scalarProduct;

//Answer :
public class Math14_002 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Math14_002());
        Logger.close();
    }

//    int p = 2;
//    int q = 2;
//    int r = 2;
//    int s = 2;

    int p = 97;
    int q = 97;
    int r = 12;
    int s = 3;

    public void solving() {
        Point[] ps = getPoints(r, s);

        int t = 0;
        for (Point p1 : ps) {
            for (Point p2 : ps) {
                for (Point p3 : ps) {
                    for (Point p4 : ps) {
                        if (isSimpleQuadrilateral(p1, p2, p3, p4)) {
//                            System.out.println(p1 + "," + p2 + "," + p3 + "," + p4);
                            t++;
                        }
                    }
                }
            }
        }
        System.out.println("Should be zero: " + t % 8);
        t /= 8;

        Point origin = new Point(0, 0);
        Point[] ps2 = getPoints(p, q);
        
        int m = 0;
        for (Point p1 : ps2) {
            if (p1.equals(origin))continue;
            for (Point p2 : ps2) {
                if (p2.equals(origin) || p2.equals(p1)) continue;
                if (isRightTriangle(origin, p1, p2)) {
//                    System.out.println(origin + "," + p1 + "," + p2);
                    m++;
                }
            }
        }
        System.out.println("Should be zero: " + m % 2);
        m /= 2;

        int n = 249 * t;

        System.out.println(m);
        System.out.println(n);
    }

    private boolean isRightTriangle(Point origin, Point p1, Point p2) {
        if (scalarProduct(origin, p1, origin, p2) == 0 || scalarProduct(p1, origin, p1, p2) == 0 || scalarProduct(p2, origin, p2, p1) == 0) {
            return true;
        }
        return false;
    }

    private Point[] getPoints(int r, int s) {
        Point[] ps = new Point[(r + 1) * (s + 1)];
        int cnt = 0;
        for (int x = 0; x <= r; ++x) {
            for (int y = 0; y <= s; ++y) {
                ps[cnt++] = new Point(x, y);
            }
        }
        return ps;
    }

    private boolean isSimpleQuadrilateral(Point p1, Point p2, Point p3, Point p4) {
        return !intersects(p1, p2, p3, p4) && !intersects(p1, p4, p2, p3);
    }
}
