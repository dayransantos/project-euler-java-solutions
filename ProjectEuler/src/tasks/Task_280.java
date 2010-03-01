package tasks;

import utils.log.Logger;

//Answer :
public class Task_280 implements ITask {

    double seedProb[][][] = new double[5][5][6];
    double freeAntProb[][][] = new double[5][5][6];
    double seedAntProb[][][] = new double[5][5][6];

    public void solving() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 5; ++j) {
                seedProb[i][j][0] = 1;
                seedProb[4][j][1] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_280());
        Logger.close();
    }
}
