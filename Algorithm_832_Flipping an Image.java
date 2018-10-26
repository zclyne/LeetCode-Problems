// 思路：为节省时间，直接在原数组A上遍历并修改，不要创建一个新的矩阵来存放结果
// 此外，将形如1 - tmp中的减号改为异或，可以进一步提高计算速度

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return A;
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = A[i][j];
                A[i][j] = 1 ^ A[i][n - 1 - j];
                A[i][n - 1 - j] = 1 ^ tmp;
            }
            if (n % 2 == 1) A[i][n / 2] = 1 ^ A[i][n / 2];
        }
        return A;
    }
}