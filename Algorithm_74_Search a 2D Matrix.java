// My Solution
// 思路：两次二分查找，第一次用于查找到target可能所在的行rowToSearch，第二次在该行中查找target的位置

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0
        || target < matrix[0][0]
        || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length, rowToSearch = 0;

        // use binary search to look for the row in which target may exists
        int rowLeft = 0, rowRight = m - 1, rowMid = (rowLeft + rowRight) / 2;
        while (rowLeft <= rowRight) {
            rowMid = (rowLeft + rowRight) / 2;
            if (matrix[rowMid][0] <= target && matrix[rowMid][n - 1] >= target) { // target can only exist in rowMid
                rowToSearch = rowMid;
                break;
            } else if (matrix[rowMid][n - 1] < target) {
                rowLeft = rowMid + 1;
            } else {
                rowRight = rowMid - 1;
            }
        }

        // use binary search to look for the target in rowToSearch
        int colLeft = 0, colRight = n - 1;
        while (colLeft <= colRight) {
            int colMid = (colLeft + colRight) / 2;
            if (matrix[rowToSearch][colMid] == target) {
                return true;
            } else if (matrix[rowToSearch][colMid] < target) {
                colLeft = colMid + 1;
            } else {
                colRight = colMid - 1;
            }
        }
        return false;
    }
}



// Better Solution
// 思路：直接看作一维的有序数组，并做二分查找

class BetterSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0
        || target < matrix[0][0]
        || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2, row = mid / n, col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}