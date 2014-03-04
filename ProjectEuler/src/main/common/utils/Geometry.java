package utils;

public class Geometry {
    public static FractionPoint getTrueIntersection(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);

        return getTrueIntersection(p1, p2, p3, p4);
    }

    public static long twistedProduct(Point a1, Point a2, Point b1, Point b2) {
        return (a2.x - a1.x)*(b2.y - b1.y) - (a2.y - a1.y)*(b2.x - b1.x);
    }

    public static FractionPoint getTrueIntersection(Point p1, Point p2, Point p3, Point p4) {
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
}
