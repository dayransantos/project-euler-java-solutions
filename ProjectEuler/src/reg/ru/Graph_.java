package reg.ru;

public class Graph_ {
    //здесь задаётся размер
    public final int height = 20;

    private char[][] res;

    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Graph_().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }


    public void solving() {
        res = new char[height][height*3];

        output(res);
    }

    private void output(char[][] res) {
        for (char[] r : res) {
            System.out.println(new String(r));
        }
    }
}
