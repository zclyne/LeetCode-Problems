// 思路：如果将原数组mat展开成一维再编号，则编号从0到m * n - 1
// 再将一维编号转换成新的r * c二维数组中的下标，分别/n和%n

class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c) { // invalid
            return mat;
        }
        
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int row = (i * c + j) / n;
                int col = (i * c + j) % n;
                res[i][j] = mat[row][col];
            }
        }

        return res;
    }
}