import java.util.HashMap;
import java.util.Map;

class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Long> map = new HashMap<>();
        for (int[] rectangle : rectangles) {
            String ratio = getWidthHeightRatio(rectangle);
            map.put(ratio, map.getOrDefault(ratio, 0L) + 1L);
        }

        long result = 0;
        for (long count : map.values()) {
            result += count * (count - 1) / 2;
        }

        return result;
    }

    private String getWidthHeightRatio(int[] rectangle) {
        int gcd = getGCD(rectangle[0], rectangle[1]);
        return rectangle[0] / gcd + "/" + rectangle[1] / gcd;
    }

    private int getGCD(int a, int b) {
        if (a < b) {
            return getGCD(b, a);
        }
        while (b > 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
}