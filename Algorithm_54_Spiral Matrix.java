import java.util.List;
import java.util.ArrayList;

// 思路：先从左到右遍历顶部一行，再从上到下遍历右边一列
// 再从右到左遍历底部一行，最后从下到上遍历左边一列
// 注意遍历底部一行和左边一列时，要先判断是否和上面一行或右边一列重合
// 如果不判断的话，可能会导致某一行或某一列被遍历两次

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int m = matrix.length, n = matrix[0].length;
        int topRow = 0, bottomRow = m - topRow - 1, leftCol = topRow, rightCol = n - topRow - 1;
        while (topRow <= bottomRow && leftCol <= rightCol) {
            // append top row
            for (int col = leftCol; col <= rightCol; col++) {
                result.add(matrix[topRow][col]);
            }
            // append right col
            for (int row = topRow + 1; row <= bottomRow; row++) {
                result.add(matrix[row][rightCol]);
            }
            // append bottom row
            if (bottomRow > topRow) {
                for (int col = rightCol - 1; col >= leftCol; col--) {
                    result.add(matrix[bottomRow][col]);
                }
            }
            // append left col
            if (leftCol < rightCol) {
                for (int row = bottomRow - 1; row >= topRow + 1; row--) {
                    result.add(matrix[row][leftCol]);
                }
            }
            topRow++; bottomRow--; leftCol++; rightCol--;
        }
        return result;
    }
}