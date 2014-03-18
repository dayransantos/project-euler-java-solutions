package tasks;

import java.io.IOException;

public class Tester {
    private static long begTime;

    public static void test(ITask task) {
        begTime = System.currentTimeMillis();

        try {
            task.solving();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nTask completed in: " + timeElapsed() + " ms.");
    }

    public static void timeStamp() {
        System.out.println("\nWorking time: " + timeElapsed() + " ms.");
    }

    public static long timeElapsed() {
        return System.currentTimeMillis() - begTime;
    }

    public static void test(Class<? extends ITask> taskClass, int times) {
        System.out.println("Running " + taskClass.getSimpleName() + ".");
        long begTime = System.currentTimeMillis();

        try {
            ITask task = taskClass.getConstructor().newInstance();
            for (int i = 0; i < times; ++i) {
                task.solving();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long time = System.currentTimeMillis() - begTime;
        System.out.println("Working time: " + time + " ms.");
    }
}
