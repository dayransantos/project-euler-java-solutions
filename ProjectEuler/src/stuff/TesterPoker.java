package stuff;

import java.util.*;

import static stuff.TesterPoker.Card.*;

public class TesterPoker {
    public static class Card {
        public static int suit(int value) {
            return value/13;
        }
        public static int kind(int value) {
            return value%13;
        }
        public static String getKindString(int value) {
            switch (kind(value)) {
                case 0 : return "2";
                case 1 : return "3";
                case 2 : return "4";
                case 3 : return "5";
                case 4 : return "6";
                case 5 : return "7";
                case 6 : return "8";
                case 7 : return "9";
                case 8 : return "T";
                case 9 : return "J";
                case 10 : return "Q";
                case 11 : return "K";
                case 12 : return "A";
            }
            return null;
        }

        public static String getSuitString(int value) {
            switch (suit(value)) {
                case 0 : return "1";
                case 1 : return "2";
                case 2 : return "3";
                case 3 : return "4";
            }
            return null;
        }

        public static String getCardString(int value) {
            return getKindString(value) + getSuitString(value);
        }

        private static boolean isCardMore(int c1, int c2) {
            int result = new Integer(kind(c1)).compareTo(kind(c2));
            if (result != 0) return result > 0;
            return new Integer(suit(c1)).compareTo(suit(c2)) > 0;
        }
        public static void sortCards(int x[]) {
            int length = x.length;
            for (int i=0; i < length; i++)
            for (int j=i; j > 0 && isCardMore(x[j-1], x[j]); j--) {
                int t = x[j];
                x[j] = x[j-1];
                x[j-1] = t;
            }
        }
    }
    
    public static class Hand implements Comparable<Hand>{
        private int cards[];
        private int sorted[];

        private int straitBestKind;
        private boolean isStrait;
        private boolean isFlush;
        private boolean isStraitFlush;
        private int freeCards[];
        private int freeCardsCnt;
        private int firstPair;
        private int secondPair;
        private int threeKind;
        private int fourKind;

        int handRating = 0;

        public Hand(int... cards) {
            this.cards = cards;
            computeProperties();
        }

        public String getKindsString() {
            if (sorted.length != 2) {
                return "";
            }
            String kindsString = "";
            boolean sameSuit = true;
            boolean sameKind = true;
            int suit = suit(sorted[0]);
            int kind = kind(sorted[0]);
            for (int i = sorted.length - 1; i >=0; i--) {
                int c = sorted[i];
                kindsString += getKindString(c);
                sameSuit &= suit(c) == suit;
                sameKind &= kind(c) == kind;
            }

            if (!sameKind) {
                kindsString += (sameSuit ? "s" : "o");
            }
            return kindsString;
        }

        public int compareTo(Hand h) {
            int result = 0;
            result = compareInts(result, h.handRating, handRating);
            if (result == 0 && isStrait) {
                return compareInts(0, straitBestKind, h.straitBestKind);
            }

            result = compareInts(result, fourKind, h.fourKind);
            result = compareInts(result, threeKind, h.threeKind);
            result = compareInts(result, firstPair, h.firstPair);
            result = compareInts(result, secondPair, h.secondPair);
            return result != 0
                   ? result
                   : compareFreeCards(freeCards, h.freeCards, freeCardsCnt);
        }

        private int compareInts(int result, int n1, int n2) {
            return result != 0
                   ? result
                   : new Integer(n1).compareTo(n2);
        }

        private int compareFreeCards(int[] c1, int[] c2, int freeCardsCnt) {
            for (int i = 0; i < freeCardsCnt; ++i) {
                int k1 = c1[i];
                int k2 = c2[i];
                if (k1 != k2) {
                    return compareInts(0, k1, k2);
                }
            }
            return 0;
        }

        public Hand findBestHand(Hand table) {
            int cnt = cards.length + table.cards.length;
            Hand bestHand = null;
            //выбираем 5 карт
            for (int a1 = 0; a1 < cnt - 4; ++a1) {
                int c1 = a1 < cards.length ? cards[a1] : table.cards[a1 - cards.length];
                for (int a2 = a1 + 1; a2 < cnt - 3; ++a2) {
                    int c2 = a2 < cards.length ? cards[a2] : table.cards[a2 - cards.length];
                    for (int a3 = a2 + 1; a3 < cnt - 2; ++a3) {
                        int c3 = a3 < cards.length ? cards[a3] : table.cards[a3 - cards.length];
                        for (int a4 = a3 + 1; a4 < cnt - 1; ++a4) {
                            int c4 = a4 < cards.length ? cards[a4] : table.cards[a4 - cards.length];
                            for (int a5 = a4 + 1; a5 < cnt; ++a5) {
                                int c5 = a5 < cards.length ? cards[a5] : table.cards[a5 - cards.length];
                                Hand h = new Hand(c1, c2, c3, c4, c5);
                                if (bestHand == null || h.compareTo(bestHand) > 0) {
                                    bestHand = h;
                                }
                            }
                        }
                    }
                }
            }

            return bestHand;
        }

        private void computeProperties() {
            sorted = cards.clone();
            sortCards(sorted);

            isFlush = false;
            isStrait = false;
            threeKind = -1;
            fourKind = -1;
            firstPair = -1;
            secondPair = -1;
            straitBestKind = -1;
            if (cards.length != 5) {
                return;
            }
            

            isFlush = true;
            int kindCounts[] = new int[13];
            for (int i = 0; i < 5; ++i) {
                int c = sorted[i];
                if (i > 0 && suit(c) != suit(sorted[i-1])) isFlush = false;

                kindCounts[kind(c)]++;
            }
            int bestKind = kind(sorted[4]);
            int secondBestKind = kind(sorted[3]);
            int worstKind = kind(sorted[0]);

            freeCardsCnt = 0;
            freeCards = new int[5];
            for (int i = 12; i >= 0; i--) {
                int kindCnt = kindCounts[i];
                switch (kindCnt) {
                    case 1: freeCards[freeCardsCnt++] = i; break;
                    case 2:
                        if (firstPair == -1) {
                            firstPair = i;
                        } else {
                            secondPair = i;
                        }
                        break;
                    case 3: threeKind = i; break;
                    case 4: fourKind = i; break;
                }
            }

            isStrait = freeCardsCnt==5 && ((bestKind - worstKind==4) || (bestKind==12 && worstKind==0 && secondBestKind==3));
            if (isStrait) {
                straitBestKind = bestKind != 12
                                 ? bestKind
                                 : worstKind==0 ? 3 : 12;
            }

            isStraitFlush = isStrait && isFlush;

            handRating = isStraitFlush
                         ? 0
                         : fourKind != -1
                           ? 1
                           : firstPair != -1 && threeKind != -1
                             ? 2
                             : isFlush
                               ? 3
                               : isStrait
                                 ? 4
                                 : threeKind != -1
                                   ? 5
                                   : secondPair != -1
                                     ? 6
                                     : firstPair != -1
                                       ? 7
                                       : 8
                    ;
        }

        private String getHandString() {
            switch (handRating) {
                case 0: return "Strait Flush";
                case 1: return "Four";
                case 2: return "Full House";
                case 3: return "Flush";
                case 4: return "Straight";
                case 5: return "Three";
                case 6: return "Two Pairs";
                case 7: return "Pair";
                case 8: return "High Card";
            }
            return "";
        }

        public String toString() {
            String res = "";
            for (int c : sorted) {
                res += getCardString(c) + " ";

            }
            return res + " | " + getHandString();
        }
    }

    public static class Deck {
        private static Random r = new Random();
        private boolean used[] = new boolean[52];
        int cnt = 0;

        public Deck() {
        }

        public int next() {
            int value;
            do {
                value = r.nextInt(52);
            } while (used[value]);
            used[value] = true;
            ++cnt;
            return value;
        }

        public void reserve(int n) {
            if (cnt - 5 < n) {
                Arrays.fill(used, false);
                cnt = 0;
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        Random r = new Random();
        for (int i = 0; i < 0; ++i) {
            Deck d = new Deck();
            Hand h1 = new Hand(d.next(), d.next(), d.next(), d.next(), d.next());
            Hand h2 = new Hand(d.next(), d.next(), d.next(), d.next(), d.next());

            int cmp = h1.compareTo(h2);
            if (cmp == 0) {
                System.out.println("Equals:");
            } else if (cmp == -1) {
                System.out.println("Less:");
            } else {
                System.out.println("More:");
            }

            System.out.println("  " + h1.toString());
            System.out.println("  " + h2.toString());
            System.out.println("");
        }


        int playersCount = 2;
        int totalGames = 1000000;
        Map<String, Integer> gamesPlayed = new TreeMap<String, Integer>();
        Map<String, Integer> gamesWon = new TreeMap<String, Integer>();

        Deck d = new Deck();
        for (int g = 0; g < totalGames; ++g) {
            d.reserve(5 + playersCount*2);
            Hand table = new Hand(d.next(), d.next(), d.next(), d.next(), d.next());

            List<Hand> startHands = new ArrayList<Hand>();
            List<Hand> bestHands = new ArrayList<Hand>();

            for (int i = 0; i < playersCount; ++i) {
                Hand h = new Hand(d.next(), d.next());

                startHands.add(h);
                bestHands.add(h.findBestHand(table));
            }

            Hand wonHand = bestHands.get(0);
            for (int i = 1; i < playersCount; ++i) {
                Hand currentHand = bestHands.get(i);
                if (currentHand.compareTo(wonHand) > 0) {
                    wonHand = currentHand;
                }
            }

            int wonHandsCount = 0;
            for (int i = 0; i < playersCount; ++i) {
                Hand startHand = startHands.get(i);
                Hand bestHand = bestHands.get(i);
                if (bestHand.compareTo(wonHand) == 0) {
                    wonHandsCount++;
                }
                increase(startHand, gamesPlayed);
            }

            if (wonHandsCount == 1) {
                Hand startHand = startHands.get( bestHands.indexOf(wonHand) );
                increase(startHand, gamesWon);
            }

//            for (int i = 0; i < playersCount; ++i) {
//                Hand startHand = startHands.get(i);
//                Hand bestHand = bestHands.get(i);
//                if (bestHand.compareTo(wonHand) == 0) {
//                    increase(startHand, gamesWon);
//                }
//                increase(startHand, gamesPlayed);
//            }
        }

        for (String hand : gamesWon.keySet()) {
            int played = gamesPlayed.get(hand);
            int won = gamesWon.get(hand);

            System.out.printf("%1$-3s: %2$.1f\n", hand, won*100/(double)played);
        }

        System.out.println("");
        System.out.println("");

        String hand = "22";
        int played = gamesPlayed.get(hand);
        int won = gamesWon.get(hand);

        System.out.printf("%1$-3s: %2$.1f\n", hand, won*100/(double)played);

    }

    private static void increase(Hand hand, Map<String, Integer> map) {
        String s = hand.getKindsString();
        int value = map.containsKey(s) ? map.get(s) : 0;
        map.put(s, value + 1);
    }
}
