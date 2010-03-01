package utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

    public static void writeFileLines(String fileName, List<String> lines) throws IOException {
        PrintWriter outFile = new PrintWriter(fileName);
//        PrintWriter outFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

        for (String line : lines) {
            outFile.println(line);
        }
        outFile.close();
    }
}
