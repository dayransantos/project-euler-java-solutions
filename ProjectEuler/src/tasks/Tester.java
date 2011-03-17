package tasks;

public class Tester {
    public static void test(ITask task) {
        long begTime = System.currentTimeMillis();

        task.solving();

        long time = System.currentTimeMillis() - begTime;
        System.out.println("\nWorking time: " + time + " ms.");
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
