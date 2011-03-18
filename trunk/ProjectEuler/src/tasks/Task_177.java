package tasks;

import utils.log.Logger;

import java.util.HashSet;
import java.util.Set;

//Answer :
public class Task_177 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_177());
        Logger.close();
    }

    class Rect {
        int a, b, c, d;

        public Rect(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = 360 - a - b - c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Rect)) {
                return false;
            }

            Rect rect = (Rect) o;

            return a == rect.a && b == rect.b && c == rect.c
                    || a == rect.b && b == rect.c && c == rect.d
                    || a == rect.c && b == rect.d && c == rect.a
                    || a == rect.d && b == rect.a && c == rect.b;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            return result;
        }
    }

    Set<Rect> all = new HashSet<Rect>();

    public void solving() {
        for (int a = 1; a < 180; ++a) {
            for (int b = 1; b < 180; ++b) {
                for (int c = 1; c < 180; ++c) {
                    int d = 180 - a - b - c;
                    for (int h = 1; h < 180; ++h) {
                        int g = 180 - a - b - h;
                        int f = 1;//todo;
                        int e = 0;

                        all.add(new Rect(a + h, b + c, d + e));
                    }
                }
            }
        }
        System.out.println(all.size());
    }
}
