import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;

// Based on the definitions below.
// P(x, y) = P(x-1, y+1) + P(x-1, y-1);
// P(x, 0) = 2^x.
// P(x, x) = 1.
// P(x, x-1) = 2.
// Time complexity: O(n^2) for single P values, but also O(n^2) for printing the entire Seth Triangle. DP, essentially.
public class P3 {

    // Hash map storing the P values.
    public static HashMap<Integer, HashMap<Integer, BigInteger>> map = new HashMap<>();

    // Two.
    public static BigInteger two = new BigInteger("2");

    // Smartly calculates the number of permutations of x coins reaching y distance.
    public static BigInteger P(int x, int y) throws Exception {
        if (y == x) {
            return new BigInteger("1");
        }
        if (y == x-1) {
            return new BigInteger("2");
        }
        if (map.containsKey(x) && map.get(x).containsKey(y)) {
            return map.get(x).get(y);
        }

        BigInteger lastOne= P(x-1, y+1);
        if (y == 1) {
            return lastOne.add(two.pow(x-1));
        }

        BigInteger output = lastOne.add(P(x-1, y-1));
        if (map.containsKey(x)) {
            map.get(x).put(y, output);
        }
        else {
            HashMap<Integer, BigInteger> inputMap = new HashMap<>();
            inputMap.put(y, output);
            map.put(x, inputMap);
        }

        return output;
    }

    // Prints all P(x, y) up to x.
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

    // Prints all P(x, y) up to x, but y goes backwards.
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

}
