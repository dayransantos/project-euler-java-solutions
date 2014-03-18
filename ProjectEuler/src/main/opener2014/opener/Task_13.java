package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import java.math.BigInteger;

//Answer :
public class Task_13 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_13());
        Logger.close();
    }

    public void solving() {
        String s = "1220021001202010211110000122220012222111";
        String s2 = s.replace('2', '5');
        String s3 = new StringBuilder(s).reverse().toString();
        String s4 = s.replace('0', '3').replace('2', '0').replace('3', '2');
        System.out.println(s3);


    }
}
