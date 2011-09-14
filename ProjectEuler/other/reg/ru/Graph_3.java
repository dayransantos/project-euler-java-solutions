package reg.ru;

import static java.lang.Math.*;

//!!работает только с моноширным шрифтом!!
public class Graph_3 {
    //радиус внешней и внутренней окружностей для точек звезды
    public final int R = 15;
    public final int r = 7;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_3().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }


    public void solving() {
        res = new char[2 * R + 4][2 * R + 4];

        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = ' ';
            }
        }

        double da = 2 * PI / 5;
        double a1 = da;
        double a2 = da/2;

        int x1 = 0;
        int y1 = R;

        for (int i = 0; i < 5; ++i) {
            int x2 = (int) (R * sin(a1));
            int y2 = (int) (R * cos(a1));

            int xs = (int) (r * sin(a2));
            int ys = (int) (r * cos(a2));

            putline(x1, y1, xs, ys);
            putline(xs, ys, x2, y2);

            x1 = x2;
            y1 = y2;

            a1 += da;
            a2 += da;
        }


        output(res);
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
        puts(R + 1 - y, R + 1 - x);
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
