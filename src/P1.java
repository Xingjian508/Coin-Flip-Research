import java.util.HashMap;
import java.util.Map;

// Recursively defined P, splitting it according to the number of heads.
// Time complexity: honestly I don't know. Need to figure this out.
public class P1 {

    // Hash map storing the R values.
    static final Map<Long, Map<Long, Long>> map = new HashMap<>();

    // Smartly calculates the number of permutations of x coins reaching y distance.
    public static long P(int x, int y) {
        long totalP = 0;
        for (int i=0; i<=x-y; i++) {
            totalP += R(x, y, y+i);
        }
        return totalP;
    }

    // Recursively calculates the number of permutations of x coins reaching y distance, with z coins facing up.
    public static long R(int x, int y, int z) {
        if (y == z) {return 1;}
        if (2*z - y >= x) {return choose(x, z);}

        long totalR = 0;

        int k1;
        int k2;
        int k3;
        int k4 = 2*z-y;
        k1 = Math.max(0, Math.min(z-y, x-k4));
        k2 = Math.min(x, k4);
        k3 = Math.max(1, x-k2);

        for (int i=0; i<=k1; i++) {
            totalR += R(k2, y, z - i) * choose(k3, i);
        }
        return totalR;
    }

    // The "choose()" function is essential to combinatorics.
    public static long choose(long total, long choose) {
        if(total < choose)
            return 0;
        if(choose == 0 || choose == total)
            return 1;

        if (!(map.containsKey(total) && map.get(total).containsKey(choose))){
            map.put(total, new HashMap<>());
            map.get(total).put(choose, choose(total-1,choose-1)+choose(total-1,choose));
        }
        return map.get(total).get(choose);
    }

}
