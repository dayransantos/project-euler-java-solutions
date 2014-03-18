package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.LongIntPair;

import java.math.BigInteger;
import java.util.*;

import static java.math.BigInteger.valueOf;
import static java.util.Collections.sort;

//Answer :
public class Task_14 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_14());
        Logger.close();
    }

    A a = new A();
    B b = new B();
    C c = new C();
    D d = new D();
    
    long aa, ab, ac, ad;
    long ba, bb, bc, bd;
    long ca, cb, cc, cd;
    long da, db, dc, dd;
    
    public void solving() {
//            A) Всегда возвращать false.
//            B) Если противник на предыдущем шаге вернул false, то изменить свой прошлый ответ, иначе его повторить; на первом шаге вернуть false.
//            C) Повторить предыдущий ответ противника; на первом шаге вернуть true.
//            D) Если противник хотя бы раз вернул false, возвращать false; на первом шаге вернуть true.

        aa = compete(a, a);
        ab = compete(a, b);
        ac = compete(a, c);
        ad = compete(a, d);

        ba = compete(b, a);
        bb = compete(b, b);
        bc = compete(b, c);
        bd = compete(b, d);

        ca = compete(c, a);
        cb = compete(c, b);
        cc = compete(c, c);
        cd = compete(c, d);

        da = compete(d, a);
        db = compete(d, b);
        dc = compete(d, c);
        dd = compete(d, d);

        long mx = 0;
        byte mxa = 0;
        byte mxb = 0;
        byte mxc = 0;
        LinkedHashMap<Long, Integer> current = new LinkedHashMap<Long, Integer>();
        ArrayList<Long> path = new ArrayList<Long>();
        
        long rr = find(1, 1, 1, 0, current, path);
        System.out.println(rr);
        for (byte a = 0; a <= 100; ++a) {
            int be = 100 - a;
            for (byte b = 0; b <= be; ++b) {
                int ce = 100 - a - b;
                for (byte c = 0; c <= ce; ++c) {
                    long r = find(a, b, c, (byte) (100-a-b-c), current, path);
                    if (r > mx) {
                        mx = r;
                        mxa = a;
                        mxb = b;
                        mxc = c;
                    }
                }
            }
        }
        byte mxd = (byte) (100 - mxa - mxb - mxc);
        System.out.println(mx);
        System.out.println(mxa + " " + mxb + " " + mxc + " " + mxd);
        BigInteger res = valueOf(2).pow(mxa);
        res = res.multiply(valueOf(3).pow(mxb));
        res = res.multiply(valueOf(5).pow(mxc));
        res = res.multiply(valueOf(7).pow(mxd));
        System.out.println(res);
    }

    private long find(int a, int b, int c, int d, LinkedHashMap<Long, Integer> current, ArrayList<Long> path) {
        return find((byte)a, (byte)b, (byte)c, (byte)d, current, path);
    }

    private Map<Long, Long> s = new HashMap<Long, Long>();
    private Map<Long, Boolean> cycle = new HashMap<Long, Boolean>();
    
    private long find(byte a, byte b, byte c, byte d, LinkedHashMap<Long, Integer> current, ArrayList<Long> path) {
        long state = getState(a, b, c, d);
        Long r = s.get(state);
        if (r == null) {

            Integer ind = current.get(state);
            if (ind != null) {
                long cycleLength = current.size() - ind;
                for (int i = ind; i < current.size(); ++i) {
                    Long st = path.get(i);
                    s.put(st, cycleLength);
                    cycle.put(st, true);
                }
                return cycleLength;
            }
            
            if (a == 0 && b == 0 && c == 0
                    || a == 0 && b == 0 && d == 0
                    || a == 0 && c == 0 && d == 0
                    || c == 0 && b == 0 && d == 0) {
                r = 0L;
            } else {
                long as = a*(a-1) * aa + a*b*ab + a*c*ac * a*d*ad;
                long bs = b*a * ba + b*(b-1)*bb + b*c*bc * b*d*bd;
                long cs = c*a * ca + c*b*cb + c*(c-1)*cc * c*d*cd;
                long ds = d*a * da + d*b*db + d*c*dc * d*(d-1)*dd;

                List<LongIntPair> all = rate(as, bs, cs, ds);
                int i = 0;
                while (getCnt(all.get(i).b, a, b, c, d) == 0) {
                    i++;
                }
                
                long mn = all.get(i).a;
                for (int j = i; j < 4; ++j) {
                    LongIntPair sm = all.get(j);
                    if (sm.a == mn) {
                        switch (sm.b) {
                            case 1: a--; break;
                            case 2: b--; break;
                            case 3: c--; break;
                            case 4: d--; break;
                        }
                    }
                }

                long mx = all.get(3).a;
                for (int j = 3; j >= 0; --j) {
                    LongIntPair sm = all.get(j);
                    if (sm.a == mx) {
                        switch (sm.b) {
                            case 1: a++; break;
                            case 2: b++; break;
                            case 3: c++; break;
                            case 4: d++; break;
                        }
                    }
                }
                current.put(state, current.size());
                path.add(state);
                r = find(a, b, c, d, current, path);
                current.remove(state);
                path.remove(path.size() - 1);
                
                if (cycle.containsKey(state)) {
                    return r;
                }
                
                r++;
            }
            s.put(state, r);
            return r;
        }
        return r;
    }

    private long getState(long a, long b, long c, long d) {
        return a + (b<<16L) + (c<<32L) + (d<<48L);
    }

    private int getCnt(int i, int a, int b, int c, int d) {
        switch (i) {
            case 1: return a;
            case 2: return b;
            case 3: return c;
            case 4: return d;
        }
        return 0;
    }

    private List<LongIntPair> rate(long as, long bs, long cs, long ds) {
        List<LongIntPair> all = new ArrayList<LongIntPair>();
        all.add(new LongIntPair(as, 1));
        all.add(new LongIntPair(bs, 2));
        all.add(new LongIntPair(cs, 3));
        all.add(new LongIntPair(ds, 4));
        sort(all);
        return all;
    }

    private int compete(Alg a, Alg b) {
        boolean aprev = a.first();
        boolean bprev = b.first();
        boolean anofalses = aprev;
        boolean bnofalses = bprev;
        int ascore = score(aprev, bprev);
        for (int step = 1; step < 100; ++step) {
            boolean acurr = a.get(aprev, bprev, bnofalses);
            boolean bcurr = b.get(bprev, aprev, anofalses);
            
            ascore += score(acurr, bcurr);
            
            aprev = acurr;
            bprev = bcurr;
            anofalses &= acurr;
            bnofalses &= bcurr;
        }
        
        return ascore;
    }

    private int score(boolean my, boolean his) {
        if (my) {
            return his ? 3 : 0;
        }
        return his ? 5 : 1;
    }
    
    interface Alg {
        boolean first();
        boolean get(boolean myprev, boolean hisprev, boolean hisnofalses);
    }

    class A implements Alg {
        @Override
        public boolean first() { return false; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return false;
        }
    }

    class B implements Alg {
        @Override
        public boolean first() { return false; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisprev ? myprev : !myprev;
        }
    }
    class C implements Alg {
        @Override
        public boolean first() { return true; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisprev;
        }
    }
    class D implements Alg {
        @Override
        public boolean first() { return true; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisnofalses;
        }
    }
}
