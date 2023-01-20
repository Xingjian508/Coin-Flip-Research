import java.io.*;
import java.util.*;

public class Pascal {

    // The triangle as a 2D array.
    public static String[][] triangle = new String[1500][];

    // Reads the pascal triangle from the pre-generated Pascal Triangle stored in a csv file. O(1) time.
    public static void makeTriangle() throws Exception {
        String thisLine;
        BufferedReader reader = null;
        try {
            File file = new File("Pascal's Triangle.csv");
            reader = new BufferedReader(new FileReader(file));
            for (int i=0; i<=1500; i++) {
                if ((thisLine = reader.readLine()) != null) {
                    triangle[i] = thisLine.split(",");
                }
                //System.out.println("Pascal triangle line " + i + " made.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Prints the triangle up to line n. Note: n <= 200.
    public static void printTriangle(int n) {
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=i; j++) {
                System.out.print(triangle[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Prints the nth diagonal, end at line end. Note: end <= 1499.
    public static void printNthDiagonal(int n, int end) {
        for (int i=n; i<=end; i++) {
            System.out.println(triangle[i][n]);
        }
    }
}
