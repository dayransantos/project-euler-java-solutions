package reg.ru;

public class Task__17 {
    public static void main(String[] args) {
        long begTime = System.currentTimeMillis();
        new Task__17().solving();
        System.out.println("\nTask completed in: " + (System.currentTimeMillis() - begTime) + " ms.");
    }

    public void solving() {
        int s = 0;
        for (int i = 1; i < 1001; ++i) {
            s += getSymbolCounts(i);
        }
        System.out.println(s);
    }

    public static int getSymbolCounts(int n) {
        String hundStr = "hundred";
        String ones[] = { "","one","two","three","four","five","six","seven","eight","nine"};
        String tens[] = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String overtens[] = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        if (n == 1000) {
            return "onethousand".length();
        }
        if (n < 10) return ones[n].length();
        if (n < 20) return overtens[n-10].length();

        if (n < 100) {
            return tens[n/10].length() + ones[n%10].length();
        }

        int res = 0;
        res += ones[n/100].length() + hundStr.length();
        n %= 100;

        if (n == 0) return res;

        res += 3;

        if (n < 10) return res + ones[n].length();
        if (n < 20) return res + overtens[n-10].length();

        return res + tens[n/10].length() + ones[n%10].length();
    }
}
