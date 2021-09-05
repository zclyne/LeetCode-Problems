import java.util.Arrays;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int n = properties.length;
        int result = 0;

        Arrays.sort(properties, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int maxDefense = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (maxDefense > properties[i][1]) {
                result++;
            }
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }
        
        return result;
    }
}