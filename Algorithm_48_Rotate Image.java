// 思路：直接看出交换的顺序
// 注意嵌套循环的循环终止条件
// 由最外圈开始逐渐向内，每层大循环完成一圈的元素交换

class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        for (int upperRow = 0; upperRow < n / 2; upperRow++) {
            int leftCol = upperRow, rightCol = n - 1 - leftCol, bottomRow = n - 1 - upperRow; 
            for (int upperCol = leftCol; upperCol < rightCol; upperCol++) {
                int rightRow = upperCol, bottomCol = n - 1 - upperCol, leftRow = n - 1 - upperCol;
                int tmp = matrix[upperRow][upperCol];
                matrix[upperRow][upperCol] = matrix[leftRow][leftCol];
                matrix[leftRow][leftCol] = matrix[bottomRow][bottomCol];
                matrix[bottomRow][bottomCol] = matrix[rightRow][rightCol];
                matrix[rightRow][rightCol] = tmp;
                
            }
        }
    }
}