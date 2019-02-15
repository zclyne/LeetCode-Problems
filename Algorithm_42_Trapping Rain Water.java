// 2 Pointer Solution
// 思路：同时记录左右两个壁的最大值，固定较高的一边，并将另一边向中间移动
// 例如，当左边壁较高时，将右壁向左移动。若当前高度低于右壁出现过的最高的高度，则当前格子可以收集水
// volume += maxRight - height[right]
// 持续移动右壁，直到左壁低于右壁，此时需要将左壁向右移动，同样地，若height[left] < maxLeft，则可以收集水
// 当左右壁移动到同一格时，计算完毕

class Solution {
    public int trap(int[] height) {
        int volume = 0, left = 0, right = height.length - 1, maxLeft = 0, maxRight = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                } else {
                    volume += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right];
                } else {
                    volume += maxRight - height[right];
                }
                right--;
            }
        }
        return volume;
    }
}

// Stack Solution
// 思路：当高度不断下降时，把对应下标存入stack
// 当当前高度大于栈顶的bar的高度时，第一次弹栈得到的元素作为底，再弹栈得到左壁的高度
// 取两壁高度较小值与底的高度之差为矩形的高度、两壁下标之差为宽度，求矩形面积
// 该矩形就是这两个bar之间矩形部分的水的体积

// class Solution {
//     public int trap(int[] A) {
//         if (A==null) return 0;
//         Stack<Integer> s = new Stack<Integer>();
//         int i = 0, maxWater = 0, maxBotWater = 0;
//         while (i < A.length){
//             if (s.isEmpty() || A[i]<=A[s.peek()]){
//                 s.push(i++);
//             }
//             else {
//                 int bot = s.pop();
//                 maxBotWater = s.isEmpty()? // empty means no il
//                 0:(Math.min(A[s.peek()],A[i])-A[bot])*(i-s.peek()-1);
//                 maxWater += maxBotWater;
//             }
//         }
//         return maxWater;
//     }
// }