import java.util.List;
import java.util.ArrayList;

// 思路：先从左到右遍历顶部一行，然后把行的起始下标++
// 再从上到下遍历右边一列，然后把列的终止下标--
// 再从右到左遍历底部一行，然后把行的终止下标--
// 最后从下到上遍历左边一列，然后把列的起始下标++
// 注意每次++或--完成后，判断起始下标是否大于终止下标，若满足，则退出循环以避免重复遍历某一行或某一列

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int startRow = 0, endRow = matrix.length - 1, startCol = 0, endCol = matrix[0].length - 1;
        while (true) {

            for (int curCol = startCol; curCol <= endCol; curCol++) { // top-left to top-right
                res.add(matrix[startRow][curCol]);
            }
            startRow++;
            if (startRow > endRow) break;
            
            for (int curRow = startRow; curRow <= endRow; curRow++) { // top-right to bottom-right
                res.add(matrix[curRow][endCol]);
            }
            endCol--;
            if (startCol > endCol) break;
            
            for (int curCol = endCol; curCol >= startCol; curCol--) { // bottom-right to bottom-left
                res.add(matrix[endRow][curCol]);
            }
            endRow--;
            if (startRow > endRow) break;
            
            for (int curRow = endRow; curRow >= startRow; curRow--) { // bottom-left to top-left
                res.add(matrix[curRow][startCol]);
            }
            startCol++;
            if (startCol > endCol) break;
            
        }
        return res;
    }
}