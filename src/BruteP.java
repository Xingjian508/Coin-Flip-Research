import java.math.BigDecimal;
import java.math.BigInteger;

// Brute force calculate the permutations of P(x, y).
// Time complexity: O(2^n). Exponential.
public class BruteP {

    // Brute force calculates the number of permutations of x coins reaching y distance.
    public static int bruteP(int x, int y) {
        /*
         Brute force, O(2^x) time.

         Function:
         - Simulates all the possible permutations of x coins through an x-sized boolean array.
         - Uses reached() to examine each array; count the total number of arrays that reach the target.

         Note: x = 20 is pushing the limits.

        */
        int howManyReach = 0;
        BigInteger bi = BigInteger.ZERO;
        BigDecimal rows = BigDecimal.valueOf(Math.pow(2, x));
        while (bi.compareTo(rows.toBigInteger()) < 0) {
            StringBuilder bin = new StringBuilder(bi.toString(2));//Integer.toBinaryString(i);
            while (bin.length() < x)
                bin.insert(0, "0");
            char[] chars = bin.toString().toCharArray();
            boolean[] boolArray = new boolean[x];
            for (int j = 0; j < chars.length; j++) {
                boolArray[j] = chars[j] == '0';
            }
            howManyReach += reached(boolArray, y);
            bi = bi.add(BigInteger.ONE);
        }
        return howManyReach;
    }

    // Input an array and see if it reaches the target.
    public static int reached(boolean[] list, int y) {

        int k = 0;
        for (boolean b : list) {
            if (b) {
                k++;
            } else {
                k--;
            }
            if (k == y) {
                return 1;
            }
        }
        return 0;
    }

}
