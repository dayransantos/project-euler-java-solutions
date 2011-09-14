package reg.ru;

import static java.lang.Math.*;

//!!работает только с моноширным шрифтом!!
public class Graph_1 {
    public final int k = 5;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_1().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }


    public void solving() {
        res = new char[5 * k + 4][6 * k + 4];

        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = ' ';
            }
        }

        double a = 0;
        double da = 2*PI/200;

        int x1 = x(a);
        int y1 = y(a);

        while (a<=2*PI) {
            a+=da;

            int x2 = x(a);
            int y2 = y(a);

            putline(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }


        output(res);
    }

    private int y(double a) {
        return (int) (r(a) * sin(a));
    }

    private int x(double a) {
        return (int) (r(a) * cos(a));
    }

    private double r(double a) {
        return 2*k - 2*k*sin(a)  + k*sin(a)*sqrt(abs(cos(a)))/(sin(a) + 1.8);
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
        puts(k - y, 3*k + 1 - x);
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
