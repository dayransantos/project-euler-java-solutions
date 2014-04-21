package tasks;

public abstract class AbstractTask implements ITask {
    public static void progress100(int progress) {
        progress(progress, 100);
    }
    
    public static void progress1000(int progress) {
        progress(progress, 1000);
    }
    
    public static void progress10000(int progress) {
        progress(progress, 10000);
    }
    
    public static void progress100000(int progress) {
        progress(progress, 100000);
    }
    
    public static void progress1000000(int progress) {
        progress(progress, 1000000);
    }
    
    public static void progress(int progress, int mod) {
        if (progress % mod == 0) {
            progress("Progress: ", progress);
        }
    }
    
    public static void progress(int progress) {
        progress("Progress: ", progress);
    }
    
    public static void progress(String message, int progress) {
        System.out.println(message + progress);
    }
}
