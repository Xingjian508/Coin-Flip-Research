import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

// Much improved dynamic programming approach. Still using a recursively defined sequence C, but much faster.
// Time complexity: O(n^2 * O(choose)). Since we use HashMap, O(choose) is likely O(1), but I'm unsure. Need to ponder.
// Computes up to x = 1000.
public class P2 {

    // Smartly calculates the number of permutations of x coins reaching y distance.
    public static BigInteger P(int x, int y) throws Exception {
        // Create a pascal triangle up to 1000, from reading a csv file saved previously.
        // This saves the runtime from O(choose) to O(1).
        if (Pascal.triangle[x] == null) {
            Pascal.makeTriangle();
        }

        BigInteger[] C = new BigInteger[((x-y)/2)+1];
        BigInteger p = new BigInteger("0");
        BigInteger two = new BigInteger("2");

        for (int i=0; i<C.length; i++) {
            BigInteger overlap = new BigInteger("0");
            for (int j=0; j<i; j++) {
                overlap = overlap.add(C[i-j-1].multiply(choose((long) 2*(j+1), (long) j+1)));
            }
            C[i] = choose(y+((long) 2*i), (y+(long) i)).subtract(overlap);
            p = p.add(C[i].multiply(two.pow(x-y-(2*i))));
        }

        return p;
    }

    // The "choose()" function is essential to combinatorics.
    public static BigInteger choose(long total, long choose) {
        return new BigInteger(Pascal.triangle[(int) total][(int) choose]);
    }

    // Prints all P(x, y) up to x. Note: x <= 300 will take just under a minute.
    public void printAllPermutations(int x) throws Exception {
        for (int i=1; i<=x; i++) {
            for (int j=1; j<=i-1; j++) {
                System.out.print(P(i, j));
                System.out.print(" ");
            }
            System.out.print(P(i, i));
            System.out.println();
        }
    }

    // Prints all P(x, y), with y ranging from 1 to x.
    public void printPermutationsX(int x) throws Exception {
        for (int j=1; j<=x; j++) {
            System.out.println(P(x, j));
        }
    }

    // Prints all P(x, y), with x ranging from y to xMax would reach y.
    public void printPermutationsY(int xMax, int y) throws Exception {
        for (int i=y; i<=xMax; i++) {
            System.out.println(P(i, y));
        }
    }

    // Prints the probability that a random path with length x would pass distance y.
    public double Probability(int x, int y, int decimals) throws Exception {
        BigInteger two = new BigInteger("2");
        BigInteger p = P(x, y);
        BigInteger allP = two.pow(x);
        BigDecimal probability = new BigDecimal(p).divide(new BigDecimal(allP), decimals, RoundingMode.HALF_EVEN);
        double output = probability.doubleValue();
        System.out.println(probability.toPlainString());
        return output;
    }

    // Prints the probabilities that a random path with length x would pass distances ranging from 1 to y.
    public void printProbabilitiesX(int xMax, int y, int decimals) throws Exception {
        for (int i=y; i<=xMax; i++) {
            System.out.println(Probability(i, y, decimals));
        }
    }

    // Prints the probabilities that paths with lengths ranging from 1 to x would pass distance y.
    public void printProbabilitiesY(int xMax, int y, int decimals) throws Exception {
        for (int i=y; i<=xMax; i++) {
            System.out.println(Probability(i, y, decimals));
        }
    }

    // Prints all P(x, y) up to x, but y goes backwards. Note: x <= 300 will take just under a minute.
    public void printSethTriangle(int x) throws Exception {
        for (int i=1; i<=x; i++) {
            for (int j=i; j>=2; j--) {
                System.out.print(P(i, j));
                System.out.print(" ");
            }
            System.out.print(P(i, 1));
            System.out.println();
        }
    }

    // Prints the Seth Triangle, end at line end. Note: d = ceil(n/2) - 1.
    public void printNthDiagonal(int n, int end) throws Exception {
        for (int i=n; i<=end; i++) {
            System.out.println(P(i, 1+i-n));
        }
    }

}
