package opener;

import tasks.ITask;
import tasks.Tester;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
import utils.FileUtils;
import utils.log.Logger;

import java.util.ArrayList;

//Answer :
public class Task_16 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_16());
        Logger.close();
    }

    public void solving() {
        //creates the single metric to use - in this case the simple
        // Levenshtein is used, this is far from recomended as much better
        // metrics can be employed in most cases, please see the sourceforge
        // SimMetric forums for advice on the best metric to employ in
        // differing situations.
//            AbstractStringMetric metric = new Levenshtein();
//            AbstractStringMetric metric = new CosineSimilarity();
//            AbstractStringMetric metric = new EuclideanDistance();
        AbstractStringMetric metric = new MongeElkan();

        ArrayList<String> words = FileUtils.readLines("D:\\Downloads\\task.txt");
        System.out.println(words.size());

        String w = null;
        double mn = 1e10;
        double score[] = new double[words.size()];
        for (int i = 0; i < words.size(); i++) {
            double sc = score[i];
            String w1 = words.get(i);
            for (int j = i+1; j < words.size(); j++) {
                String w2 = words.get(j);
                float similarity = metric.getSimilarity(w1, w2);
                sc += similarity;
                score[j] += similarity;
            }

            if (sc < mn) {
                System.out.println("Better: " + w1 + ": " + sc);
                mn = sc;
                w = w1;
            }
        }

        System.out.println(mn);
        System.out.println(w);
    }
}
