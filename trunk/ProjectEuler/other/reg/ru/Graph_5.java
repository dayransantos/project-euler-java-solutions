package reg.ru;

//!!работает только с моноширным шрифтом!!
public class Graph_5 {
    //здесь задаётся "базовый" размер
    //итоговая надпись будет в 6 раз больше высотой
    public final int b2 = 1;
    public final int base = b2*2;
    public final int height = base*3;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_5().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }


    public void solving() {
        res = new char[height][18*b2];

        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = ' ';
            }
        }

        outputDot();
        outputR(b2*4);
        outputU(b2*11);

        output(res);
    }

    private void outputU(int x) {
        leftBar(base, x, base, base);
        leftBar(height/2, x+base, b2, b2);
        rightBar(height - 1, x + base*2, base, b2);

        vertBar(0, x, 2*base, base);
        vertBar(base*2, x+base, base, base);
        vertBar(0, x+2*base + b2, height, base);
    }

    private void outputR(int x) {
        vertBar(0, x, height, base);
        rightBar(2*base - 1, x + base, base, base);
        leftBar(0, x + 2*base, base, b2);
    }

    private void rightBar(int r, int c, int height, int width) {
        for (int j = 0; j < width; ++j) {
            int rj = r - j;
            int cj = c + j;

            put(rj, cj, '/');
            for (int i = rj - 1; i >= rj-height; --i) {
                puts(i, cj);
            }

            put(rj-height, cj, '/');
        }
    }

    private void leftBar(int r, int c, int height, int width) {
        for (int j = 0; j < width; ++j) {
            int rj = r + j;
            int cj = c + j;

            put(rj, cj, '\\');
            for (int i = rj + 1; i < rj+height; ++i) {
                puts(i, cj);
            }

            put(rj+height, cj, '\\');
        }
    }

    private void vertBar(int r, int c, int height, int width) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                puts(r + i, c + j);
            }
        }
    }

    private void puts(int i, int j) {
        put(i, j, '#');
    }

    private void outputDot() {
        for (int i = 0; i < base; ++i) {
            for (int j = 0; j < base; ++j) {
                puts(height - i - 1, j);
            }
        }
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
