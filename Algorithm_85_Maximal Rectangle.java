// DP Solution
// 思路：数组height[i][j]存储以matrix[i][j]为最低的'1'、宽度为1的矩形向上延伸的高度
// left[i][j]存储以height[i][j]为高、宽度为1的矩形向左可以到达的最小的坐标
// right[i][j]存储以height[i][j]为高、宽度为1的矩形向右可以到达的最大的坐标 + 1
// 则matrix[i][j]对应的矩形面积是(right[i][j] - left[i][j]) * height[i][j]
// 此处把二维数组优化成了一维数组

import java.util.Arrays;
import java.util.Stack;
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        for (int i = 0; i < m; i++) {
            int curLeft = 0; // curLeft is the first index of continuous '1's of the rectangle in matrix[i]
            int curRight = n; // curRight is 1 + the last index of continuous '1's of the rectangle in matrix[j]

            // update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') { // height[i][j] = height[i - 1][j] + 1
                    height[j]++;
                } else { // height[i][j] = 0
                    height[j] = 0;
                }
            }

            // update left
            for (int j = 0; j < n; j++) { // from left to right
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft); // before updating, left[j] is the 1st index of continuous '1's in matrix[i - 1]
                } else {
                    left[j] = 0; // invalid
                    curLeft = j + 1; // matrix[i][j] = '0', we need to find the new curLeft, which is at least j + 1
                }
            }

            // update right
            for (int j = n - 1; j >= 0; j--) { // from right to left
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j; // not j - 1, because right[j] is 1 + last index of '1'
                }
            }

            // compute area
            for (int j = 0; j < n; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }

        return res;
    }
}

// Stack Solution
// 思路：对每一层都使用Algorithm_84的stack解法计算一遍

class Solution2 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int res = 0;

        for (int row = 0; row < matrix.length; row++) {
            Stack<Integer> stack = new Stack<>(); // stack saves index, not the height itself
            for (int i = 0; i < n + 1; i++) {
                if (i < n) {
                    if (matrix[row][i] == '1') {
                        height[i]++; // height[i][j] = height[i - 1][j] + 1
                    } else {
                        height[i] = 0;
                    }
                }
                // keep popping until height[stack.peek()] is greater than the current height
                while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                    int curArea = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                    res = Math.max(res, curArea);
                }
                stack.push(i);
            }
        }

        return res;
    }
}