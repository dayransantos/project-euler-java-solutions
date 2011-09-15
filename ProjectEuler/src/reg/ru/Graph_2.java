package reg.ru;

//!!работает только с моноширным шрифтом!!
public class Graph_2 {
    //размер
    public final int s = 50;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_2().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }


    public void solving() {
        res = new char[s+1][s+1];

        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = ' ';
            }
        }

        int h = (2*s-3)/6 * 2 + 1;

        int cx = h/2+1;
        int cy = 3*h/2;

        rect(1, 1, h, h);

        rect(5*h/8, 1, h/4, h/2);

        diag(1, h, cx, cy);
        diag(h, h, cx, cy);

        output(res);
    }

    private void diag(int x1, int y1, int x2, int y2) {
        int dx = x1 > x2 ? -1 : 1;
        int dy = y1 > y2 ? -1 : 1;

        puts(y1, x1);
        while (x1 != x2) {
            x1 += dx;
            y1 += dy;

            puts(y1, x1);
        }
    }

    public void rect(int x0, int y0, int xs, int ys) {
        for (int i = 0; i < xs; ++i) {
            puts(y0, x0 + i);
            puts(y0 + ys - 1, x0 + i);
        }

        for (int i = 0; i < ys; ++i) {
            puts(y0 + i, x0);
            puts(y0 + i, x0 + xs - 1);
        }
    }

    private void puts(int i, int j) {
        put(s - 1 - i, j, '#');
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
