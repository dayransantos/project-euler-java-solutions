package utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.text.NumberFormatter;

public class OtherUtils {
    public static boolean isPalindrom(String s) {
        return new StringBuffer(s).reverse().toString().equals(s);
    }

    public static boolean isPandigital(String s) {
        if (s.length() > 10) return false;
        try {
            return isPandigital(Long.parseLong(s), s.length());
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isPandigital(long n, int end) {
        return isPandigital(n, 1, end);
    }

    public static boolean isPandigital(long n, int beg, int end) {
        assert (beg>=0 && beg<=9 &&
                end>=0 && end<=9 &&
                beg <= end );

        // 0 is pandigital... or maybe not
        if (n == 0) return beg == 0;

        boolean used[] = new boolean[10];
        int count = 0;

        while (n != 0) {
            int d = (int) (n%10);

            if (d<beg || d>end) return false;

            if (used[d]) return false;
            used[d] = true;
            ++count;

            n /= 10;
        }

        return count==end-beg+1;
    }

    public static int getSymbolCounts(int n) {
        String hundStr = "hundred";
        String ones[] = { "","one","two","three","four","five","six","seven","eight","nine"};
        String tens[] = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String overtens[] = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        if (n < 10) return ones[n].length();
        if (n < 20) return overtens[n-10].length();

        if (n < 100) {
            return tens[n/10].length() + ones[n%10].length();
        }

        int res = 0;
        res += ones[n/100].length() + hundStr.length();
        n %= 100;

        if (n == 0) return res;

        if (n < 10) return res + 3 + ones[n].length();
        if (n < 20) return res + 3 + overtens[n-10].length();

        return res + 3 + tens[n/10].length() + ones[n%10].length();
    }

    public static String arrayToString(long array[], int from, int count) {
        StringBuilder b = new StringBuilder("");
        b.append("[");
        for (int i = 0; i < count; ++i) {
            if (i != 0) b.append(", ");
            b.append( array[from++] );
        }
        b.append("]");
        return b.toString();
    }

    public static String arrayToString(long array[]) {
        return arrayToString(array, 0, array.length);
    }

    public static String arrayToString(int array[], int from, int count) {
        StringBuilder b = new StringBuilder("");
        b.append("[");
        for (int i = 0; i < count; ++i) {
            if (i != 0) b.append(", ");
            b.append( array[from++] );
        }
        b.append("]");
        return b.toString();
    }

    public static String arrayToString(int array[]) {
        return arrayToString(array, 0, array.length);
    }

    public static String arrayToString(String array[], int from, int count) {
        StringBuilder b = new StringBuilder("");
        b.append("[");
        for (int i = 0; i < count; ++i) {
            if (i != 0) b.append(", ");
            b.append( array[from++] );
        }
        b.append("]");
        return b.toString();
    }

    public static String arrayToString(String array[]) {
        return arrayToString(array, 0, array.length);
    }

    public static void deepFillLong(Object array, long val) {
        if (array instanceof long[]) {
            Arrays.fill((long[]) array, val);
        } else {
            Object[] a = (Object[]) array;
            for (Object subArr : a) {
                deepFillLong(subArr, val);
            }
        }
    }

    public static void deepFillInt(Object array, int val) {
        if (array instanceof int[]) {
            Arrays.fill((int[]) array, val);
        } else {
            Object[] a = (Object[]) array;
            for (Object subArr : a) {
                deepFillInt(subArr, val);
            }
        }
    }

    public static void deepFillDouble(Object array, int val) {
        if (array instanceof double[]) {
            Arrays.fill((double[]) array, val);
        } else {
            Object[] a = (Object[]) array;
            for (Object subArr : a) {
                deepFillDouble(subArr, val);
            }
        }
    }

    public static String toIndentedString(int n) {
        if (n < 10) {
            return "   " + n;
        } else if (n < 100) {
            return "  " + n;
        } else if (n < 1000) {
            return " " + n;
        } else {
            return "" + n;
        }
    }
}
