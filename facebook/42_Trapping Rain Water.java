import java.util.Deque;
import java.util.LinkedList;

// monotonicallly decreasing stack

// class Solution {
//     public int trap(int[] height) {
//         Deque<Integer> stack = new LinkedList<>();
//         int result = 0;
//         for (int i = 0; i < height.length; i++) {
//             int curHeight = height[i];
//             while (!stack.isEmpty() && height[stack.peek()] < curHeight) {
//                 int topIdx = stack.pop();
//                 int bottomHeight = height[topIdx];
//                 if (!stack.isEmpty()) {
//                     int leftWallIdx = stack.peek();
//                     int waterHeight = Math.min(height[leftWallIdx], curHeight);
//                     result += (waterHeight - bottomHeight) * (i - leftWallIdx - 1);
//                 }
//             }
//             stack.push(i);
//         }
//         return result;
//     }
// }

// two pointers

class Solution {
    public int trap(int[] height) {
        int result = 0, left = 0, right = height.length - 1;
        int maxLeft = height[left], maxRight = height[right];

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < maxLeft) {
                    result += Math.min(maxLeft, height[right]) - height[left];
                } else {
                    maxLeft = height[left];
                }
                left++;
            } else {
                if (height[right] < maxRight) {
                    result += Math.min(maxRight, height[left]) - height[right];
                } else {
                    maxRight = height[right];
                }
                right--;
            }
        }
        return result;
    }
}