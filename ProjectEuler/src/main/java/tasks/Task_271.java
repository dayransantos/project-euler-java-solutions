package tasks;

import utils.log.Logger;

//Answer :
public class Task_271 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_271());
        Logger.close();
    }

    public void solving() {
        //see: http://oeis.org/A060839 for some thoughts
        //
        //x^3 = 1 mod n
        //(x-1)*(x^2 + x + 1) = 0 mod n;
        //(x-1)((x-1)^2 - 3x)
        //(x-1)^3 - 3x(x-1) = 0 mod n
        //
        //
        //n = 13082761331670030 = 2*3*5*7*11*13*17*19*23*29*31*37*41*43
        //COUNT (p=3k+1) = {7, 13, 19, 31, 37, 43} = 3^6 - 1 = 728
        //
        //x^3 = 1 mod [2, 3, ..., 43]
        //
        //x^3 = k*n + 1
    }
}
