package math14;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Math14_001 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Math14_001());
        Logger.close();
    }

    public void solving() {
        System.out.println("OK");

    }
}
