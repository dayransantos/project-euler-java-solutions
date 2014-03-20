package opener;

import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.*;

//Answer :
public class Task_20 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_20());
        Logger.close();
    }

    public void solving() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\result_list.txt"));
        try {
            int gr = 1000;
            String line;
            while ((line = in.readLine()) != null) {
                File groupDir = new File("D:\\7\\" + gr++);
                groupDir.mkdirs();

                String[] names = line.split(";");
                for (String name : names) {
                    File f = new File("D:\\Downloads\\task\\", new File(name).getName());
                    if (!f.exists()) {
                        throw new IllegalStateException();
                    }

                    FileUtils.copy(f, new File(groupDir, f.getName()));
                }
            }
        } finally {
            in.close();
        }
    }
}
