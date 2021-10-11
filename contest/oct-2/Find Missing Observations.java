import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<Integer, List<Integer>> numToPath;

    public int[] missingRolls(int[] rolls, int mean, int n) {
        numToPath = new HashMap<>();
        int m = rolls.length;
        int sum = mean * (m + n);
        for (int roll : rolls) {
            sum -= roll;
        }
        if (sum > 6 * n || sum < n) {
            return new int[0];
        }
        int[] result = new int[n];
        int i = 0;
        while (n > 0) {
            int cur = 0;
            if (sum > 5 * n) {
                cur = 6;
            } else if (sum > 4 * n) {
                cur = 5;
            } else if (sum > 3 * n) {
                cur = 4;
            } else if (sum > 2 * n) {
                cur = 3;
            } else if (sum > n) {
                cur = 2;
            } else {
                cur = 1;
            }
            result[i++] = cur;
            sum -= cur;
            n--;
        }
        return result;
    }
}