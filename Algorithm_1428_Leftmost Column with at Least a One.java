/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

// 方法1：从右上角开始线性查找
// 如果当前格子是0，则移动到下一行
// 如果当前各自是1，则移动到左边一列
// 该方法对于列上的顺序没有要求

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0), n = dimensions.get(1);

        int result = n;
        int curRow = 0, curCol = n - 1;
        while (curRow < m && curCol >= 0) {
            if (binaryMatrix.get(curRow, curCol) == 0) {
                curRow++;
            } else {
                result = curCol;
                curCol--;
            }
        }
        return result == n ? -1 : result;
    }
}

// 方法2：二分查找
// 对每一行使用二分查找

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0), n = dimensions.get(1);

        int result = n;
        for (int i = 0; i < m; i++) {
            result = Math.min(result, binarySearch(binaryMatrix, i, n));
        }
        return result == n ? -1 : result;
    }

    private int binarySearch(BinaryMatrix binaryMatrix, int row, int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (binaryMatrix.get(row, mid) == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}