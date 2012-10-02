package tasks;

import utils.log.Logger;

//Answer :
public class Task_156 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_156());
        Logger.close();
    }

    public void solving() {
//
//        s(1) = 22786974071
//
//        50000+99982+9*4000+10000+9*300+1000+9*20+100+8*1+10+1
//        99982+10000+1000+100+10+1
//
//        1*5*10^4 + 9*4*10^3 + 9*3*10^2 + 9*2*10^1 + 8*1*10^0 +
//        99982+10000+1000+100+10+1
//
//        76348
//        7*4*10^3 + 6*3*10^2 + 3*2*10^1 + 4*1*10^0 + 10000+1000+49+10+1
//
//        long n = 199981;
//        long n = 100000000L;
//        int d = 1;
//        long res = 0;
//        for (long i = 1; i <= n; ++i) {
//            res += numOfDigit(i, d);
//        }
//        System.out.println(res);

        long cnt[] = new long[10];
        long res = 0;

        long begTime = System.currentTimeMillis();
        for (int d1 = 0; d1 < 10; ++d1) {
            System.out.println("Progress: " + d1 + " in " + (System.currentTimeMillis() - begTime) + " ms.");
            long n1 = d1*1000000000;
            for (int d2 = 0; d2 < 10; ++d2) {
                long n2 = n1 + d2*100000000;
                for (int d3 = 0; d3 < 10; ++d3) {
                    long n3 = n2 + d3*10000000;
                    for (int d4 = 0; d4 < 10; ++d4) {
                        long n4 = n3 + d4*1000000;
                        for (int d5 = 0; d5 < 10; ++d5) {
                            long n5 = n4 + d5*100000;
                            for (int d6 = 0; d6 < 10; ++d6) {
                                long n6 = n5 + d6*10000;
                                for (int d7 = 0; d7 < 10; ++d7) {
                                    long n7 = n6 + d7*1000;
                                    for (int d8 = 0; d8 < 10; ++d8) {
                                        long n8 = n7 + d8*100;
                                        for (int d9 = 0; d9 < 10; ++d9) {
                                            long n9 = n8 + d9*10;
                                            for (int d10 = 0; d10 < 10; ++d10) {
                                                long n10 = n9 + d10;
                                                cnt[d1]++;
                                                cnt[d2]++;
                                                cnt[d3]++;
                                                cnt[d4]++;
                                                cnt[d5]++;
                                                cnt[d6]++;
                                                cnt[d7]++;
                                                cnt[d8]++;
                                                cnt[d9]++;
                                                cnt[d10]++;
                                                if (cnt[1] == n10) {
                                                    System.out.println(n10);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(res);

        System.out.println((System.currentTimeMillis() - begTime) + " ms.");
    }

    private long numOfDigit(long n, int d) {
        int res = 0;
        while (n != 0) {
            if (n % 10 == d) {
                ++res;
            }
            n /= 10;
        }
        return res;
    }
}
