import java.util.HashMap;
import java.util.Map;

// 方法：二维的subArray sum = k问题
// 用二维前缀和来做
// 一维问题思路参考LC 560

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // compute presum for each row
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int result = 0;
        for (int leftCol = 0; leftCol < n; leftCol++) {
            for (int rightCol = leftCol; rightCol < n; rightCol++) {
                Map<Integer, Integer> preSumCountMap = new HashMap<>();
                preSumCountMap.put(0, 1);
                int preSum = 0;
                // for each row, calculate the sum of all elements in the matrix
                // form by the 0-th row, the row-th row, the leftCol-th column and the rightCol-th column
                for (int row = 0; row < m; row++) {
                    int curRowSum = matrix[row][rightCol] - (leftCol == 0 ? 0 : matrix[row][leftCol - 1]);
                    preSum += curRowSum;
                    result += preSumCountMap.getOrDefault(preSum - target, 0);
                    preSumCountMap.put(preSum, preSumCountMap.getOrDefault(preSum, 0) + 1);
                }
            }
        }
        return result;
    }
}