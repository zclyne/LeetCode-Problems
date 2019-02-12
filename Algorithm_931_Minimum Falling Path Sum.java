// 思路：对每一个元素A[i][j]，它所能到达的后继元素是确定的，一定是下一行的第j - 1、j或j + 1列
// 因此对于每个元素，要使得到达该元素的路径上的所有数之和最小，只需要找A[i - 1][j - 1]、A[i - 1][j]和A[i - 1][j + 1]中最小的一个加到A[i][j]上
// 并用这个和取代原来的A[i][j]成为新的A[i][j]
// 重复以上操作直到最后一行时，找最后一行中的最小值即为答案

class Solution {
    public int minFallingPathSum(int[][] A) {
        for (int i = 1; i < A.length; i++) { // row
            for (int j = 0; j < A.length; j++) { // column
                if (j == 0) {
                    A[i][j] = Math.min(A[i - 1][j], A[i - 1][j + 1]) + A[i][j];
                } else if (j == A.length - 1) {
                    A[i][j] = Math.min(A[i - 1][j - 1], A[i - 1][j]) + A[i][j];
                } else {
                    int tmp = Math.min(A[i - 1][j], A[i - 1][j + 1]);
                    tmp = Math.min(tmp, A[i - 1][j - 1]);
                    A[i][j] = tmp + A[i][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            res = Math.min(res, A[A.length - 1][i]);
        }
        return res;
    }
}