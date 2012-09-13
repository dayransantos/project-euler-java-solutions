package reg.ru;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task__19 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__19().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        System.out.println(new GregorianCalendar(1900, 0, 1).get(Calendar.DAY_OF_WEEK));

        int s = 0;
        for (int y = 1901; y < 2001; ++y) {
            for (int m = 0; m < 12; ++m) {
                if (new GregorianCalendar(y, m, 1).get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    s++;
                }
            }
        }
        System.out.println(s);
    }
}
