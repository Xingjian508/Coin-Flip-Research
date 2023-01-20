import java.lang.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {



    }

    public static void printIndexes(int start, int end) {
        for (int i=start; i<=end; i++) {
            System.out.println(i);
        }
    }

    public static long nanoToSeconds(long nanoseconds) {
        return TimeUnit.SECONDS.convert(nanoseconds, TimeUnit.NANOSECONDS);
    }

}
