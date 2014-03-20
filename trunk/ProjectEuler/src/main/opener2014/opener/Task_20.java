package opener;

import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Answer :
public class Task_20 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_20());
        Logger.close();
    }

    public void solving() throws IOException {
        int group = 0;
        Map<String, Integer> g = new HashMap<String, Integer>();
        Map<Integer, Set<String>> all = new HashMap<Integer, Set<String>>();
        for (int i = 1; i <= 5; ++i) {
            for (String line : FileUtils.readLines("d:\\6-task\\list" + i)) {
//            for (String line : FileUtils.readLines("d:\\6-task\\sample" + i)) {
                Set<String> members = new HashSet<String>();
                int newGri = group++;

                String[] ss = line.split(";");
                for (String s : ss) {
                    Integer gri = g.get(s);
                    if (gri != null) {
                        Set<String> oldGroup = all.remove(gri);
                        for (String oldm : oldGroup) {
                            g.remove(oldm);
                        }
                        members.addAll(oldGroup);
                    }
                    members.add(s);
                }

                for (String m : members) {
                    g.put(m, newGri);
                }

                all.put(newGri, members);
            }
        }

        System.out.println(all.size());

//        for (Set<String> k : all.values()) {
//            long sum = 0;
//            if (k.size() == 3) {
//                for (String s : k) {
//                    sum += Long.parseLong(s, 36);
//                }
//                System.out.println(k);
//                System.out.println(sum);
//                System.out.println(Long.toString(sum, 36).toUpperCase());
//            }
//        }
        group = 999;
        for (Set<String> k : all.values()) {
            ++group;
            File groupDir = new File("D:\\7\\" + k.size() + "-" + group + "\\");
            groupDir.mkdirs();
            for (String s : k) {
                FileUtils.copy(new File("D:\\Downloads\\task\\" + s + ".mov"), new File(groupDir,  s + ".mov"));
            }
        }

//        try {
//            int gr = 1000;
//            String line;
//            while ((line = in.readLine()) != null) {
//                File groupDir = new File("D:\\7\\" + gr++);
//                groupDir.mkdirs();
//
//                String[] names = line.split(";");
//                for (String name : names) {
//                    File f = new File("D:\\Downloads\\task\\", new File(name).getName());
//                    if (!f.exists()) {
//                        throw new IllegalStateException();
//                    }
//
//                    FileUtils.copy(f, new File(groupDir, f.getName()));
//                }
//            }
//        } finally {
//            in.close();
//        }


//        BufferedReader in = new BufferedReader(new FileReader("D:\\result_list.txt"));
//        try {
//            int gr = 1000;
//            String line;
//            while ((line = in.readLine()) != null) {
//                File groupDir = new File("D:\\7\\" + gr++);
//                groupDir.mkdirs();
//
//                String[] names = line.split(";");
//                for (String name : names) {
//                    File f = new File("D:\\Downloads\\task\\", new File(name).getName());
//                    if (!f.exists()) {
//                        throw new IllegalStateException();
//                    }
//
//                    FileUtils.copy(f, new File(groupDir, f.getName()));
//                }
//            }
//        } finally {
//            in.close();
//        }
    }
}
