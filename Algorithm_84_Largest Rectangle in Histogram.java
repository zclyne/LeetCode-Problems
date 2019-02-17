// 思路：以heights[i]为最矮的bar的矩形的最大面积等于heights[i] * (r - l - 1)
// 其中r为从i开始向右走的第一个比heights[i]更矮的bar的下标，l为从i开始向左走的第一个比heights[i]更矮的bar的下标
// 分别存储在数组lessFromLeft和lessFromRight中
// 找lessFromLeft[i]和lessFromRight[i]的最直接方法为线性向两边扩展，时间复杂度为O(n^2)
// 利用以下trick来优化到O(n): 如果heights[i - 1] < heights[i]，则lessFromLeft[i] = i - 1
// 否则，令p = i - 1, p = lessFromLeft[p]，比较heights[p]与heights[i]的关系
// 持续比较直到heights[p] < heights[i]或p == -1为止
// 对于lessFromRight，同理
// 注意两次循环一次从左到右，一次从右到左
// 最后遍历一遍，找到maxArea

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] lessFromLeft = new int[heights.length];
        int[] lessFromRight = new int[heights.length];
        lessFromLeft[0] = -1;
        lessFromRight[heights.length - 1] = heights.length;
        for(int i = 0; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        for(int i = heights.length - 1; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, (lessFromRight[i] - lessFromLeft[i] - 1) * heights[i]);
        }
        return maxArea;
    }
}


// Stack Solution
// 思路：当当前bar的高度高于栈顶的bar的高度时，表明还有继续增大宽度的可能，把当前bar的下标入栈
// 若当前bar的高度低于栈顶的bar的高度，则pop出栈顶的bar，并以该bar的高度为矩形的高度计算矩形的面积
// 当栈为空时，矩形的宽度即为i；当栈不为空时，矩形的宽度即为i - stack.peek() - 1
// 比较maxArea和当前计算得到的矩形面积，取较大的作为新的maxArea

// import java.util.Stack;
// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int len = heights.length, maxArea = 0;
//         Stack<Integer> stack = new Stack<>();
//         for (int i = 0; i <= len; i++) {
//             int curHeight = i == len ? 0 : heights[i];
//             if (stack.isEmpty() || heights[stack.peek()] <= curHeight) {
//                 stack.push(i);
//             } else {
//                 int tp = stack.pop();
//                 maxArea = Math.max(maxArea, (stack.isEmpty() ? i: i - stack.peek() - 1) * heights[tp]);
//                 i--;
//             }
//         }
//         return maxArea;
//     }
// }