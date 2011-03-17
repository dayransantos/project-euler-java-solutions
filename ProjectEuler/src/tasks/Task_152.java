package tasks;

import utils.Fraction;
import utils.LongFraction;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static utils.MyMath.lcm;

//Answer :
public class Task_152 implements ITask {
    private static final int MAX_NUM = 80;
    private static final LongFraction half = new LongFraction(1, 2);
    public void solving() {
        System.out.println(lcm(1,2));
        BigInteger g = BigInteger.ONE;
        for (long i = 2; i <= MAX_NUM; ++i) {
            g = lcm(g, BigInteger.valueOf(i*i));
            System.out.println("  " + 4480842240000L%(i*i));
        }
        System.out.println(g);

        int res = 0;

        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= 80; ++i) {
            b = b.multiply( BigInteger.valueOf(i));
        }
        System.out.println(b.multiply(b));


        int n = MAX_NUM - 1;
        Fraction fracs[] = new Fraction[n];
        for (int i = 2; i <= MAX_NUM; ++i) {
            fracs[i-2] = new Fraction(1, i*i);
        }

        Fraction sum = Fraction.ZERO;
        int beg = 2;
        int end = 4;
        for (int i = beg; i <= end; ++i) {
            sum = sum.add( fracs[i-2] );
            System.out.println( "  +" + fracs[i-2] + " = " + sum +" = "+ sum.doubleValue());
        }

//        HashMap<LongFraction, Integer> all = new HashMap<LongFraction, Integer>();
//        all.put( LongFraction.ZERO, 1 );
//        for (LongFraction fraction : fracs) {
//            HashMap<LongFraction, Integer> newMap = new HashMap<LongFraction, Integer>();
//            for (Map.Entry<LongFraction, Integer> entry : all.entrySet()) {
//                LongFraction f = entry.getKey().add( fraction );
//
//                Integer count = newMap.get( f );
//                if ( count == null ) count = 0;
//
//                newMap.put( f, count + 1);
//            }
//
//            for (Map.Entry<LongFraction, Integer> entry : newMap.entrySet()) {
//                LongFraction f = entry.getKey();
//
//                Integer count = all.get( f );
//                if ( count == null ) count = 0;
//
//                all.put( f, count + 1);
//            }
//        }
//
//        System.out.println(res);
    }

    private final static double halfd = 1.0/2.0;
    public void solving2() {
        int res = 0;

        int n = MAX_NUM - 1;
        double fracs[] = new double[n];
        for (int i = 2; i <= MAX_NUM; ++i) {
            fracs[i-2] = 1.0 / (double)(i*i);
        }

        HashMap<Double, Integer> all = new HashMap<Double, Integer>();
        all.put( 0.0, 1 );
        for (double fraction : fracs) {
            HashMap<Double, Integer> newMap = new HashMap<Double, Integer>();
            for (Map.Entry<Double, Integer> entry : all.entrySet()) {
                double f = entry.getKey() + fraction;

                Integer count = newMap.get( f );
                if ( count == null ) count = 0;

                newMap.put( f, count + 1);
            }

            for (Map.Entry<Double, Integer> entry : newMap.entrySet()) {
                Double f = entry.getKey();

                Integer count = all.get( f );
                if ( count == null ) count = 0;

                all.put( f, count + 1);
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_152());
    }

}
