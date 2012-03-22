package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static ArrayList<String> readLines(String fileName) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));

            String line = inFile.readLine();
            while (line != null) {
                res.add(line);
                line = inFile.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void writeFileLines(String fileName, List<String> lines) {
//        PrintWriter outFile = new PrintWriter(fileName);
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (String line : lines) {
                outFile.println(line);
            }
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
