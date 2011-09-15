package reg.ru;

import static java.lang.Math.*;

//!!работает только с моноширным шрифтом!!
public class Graph_4 {
    //Здесь задаётся внешний радиус (округляется до кратного 4м)
    public final int R = 12;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_4().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public int r;
    public int r2;
    public int ri;

    public void solving() {
        r = Math.round(R / 4.0f)*4;
        r2 = r/2;
        ri = r/6;

        res = new char[2*R + 5][2*R + 5];

        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = filled(i, j) ? '#' : ' ';
            }
        }

        double a = 0;
        double da = 2*PI/200;

        int x1 = 0;
        int y1 = r;

        while (a<=2*PI) {
            a+=da;

            int x2 = (int) (r*cos(a));
            int y2 = (int) (r*sin(a));

            putline(x1, y1, x2, y2);

            x1 = x2;
            y1 = y2;
        }

        output(res);
    }

    private boolean filled(int y, int x) {
        y -= r;
        x -= r;

        x+=0;

        if (!isInCircle(x, y, 0, 0, r) || isInCircle(x, y, 0, -r/2, ri)) {
            return false;
        }

        if (isInCircle(x, y, 0, r/2, ri)) {
            return true;
        }

        if (x > 0) {
            return isInCircle(x, y, 0, -r/2, r2);
        } else {
            return !isInCircle(x, y, 0, r/2, r2);
        }
    }

    private boolean isInCircle(int x, int y, int xc, int yc, int r) {
        return (x-xc)*(x-xc) + (y-yc)*(y-yc) <= r*r;
    }

    //используем алгоритм брезенхема:
    // http://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
    public void putline(int x0, int y0, int x1, int y1) {
        boolean steep = abs(y1 - y0) > abs(x1 - x0);
        if (steep) {
            int t = x0; x0 = y0; y0 = t;
            int b = x1; x1 = y1; y1 = b;
        }

        if (x0 > x1) {
            int t = x0; x0 = x1; x1 = t;
            int b = y0; y0 = y1; y1 = b;
        }
        int dx = x1 - x0;
        int dy = abs(y1 - y0);
        int error = dx / 2;
        int y = y0;
        int ystep = (y0 < y1) ? 1 : -1;

        for (int x = x0; x <= x1; ++x) {
            if (steep) {
                plot(y, x);
            } else {
                plot(x, y);
            }
            error = error - dy;
            if (error < 0) {
                y = y + ystep;
                error = error + dx;
            }
        }
    }

    private void plot(int x, int y) {
        puts(r - y, r - x);
    }

    private void puts(int i, int j) {
        put(i, j, '#');
    }

    private void put(int r, int c, char ch) {
        res[r][c] = ch;
    }

    private void output(char[][] res) {
        for (char[] r : res) {
            System.out.println(new String(r));
        }
    }
}
