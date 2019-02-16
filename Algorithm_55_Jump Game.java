// My Third Solution, beats 92.94%
// 思路：贪婪法，用变量maxCanReach记录所能到达的最远的index
// 循环变量i的上限取nums的长度和maxCanReach中较小的值，每次循环内都比较maxCanReach和nums[i] + i
// 后者代表从当前下标i开始所能到达的最远的下标
// 当maxCanReach >= nums.length - 1时，说明已经可以到达最后一个index，返回true
// 否则返回false

class Solution {
    public boolean canJump(int[] nums) {
        int maxCanReach = 0;
        for (int i = 0; i <= Math.min(nums.length - 1, maxCanReach); i++) {
            maxCanReach = Math.max(maxCanReach, nums[i] + i);
            if (maxCanReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}


// My Second Solution, TLE

// class Solution {
//     public boolean canJump(int[] nums) {
//         return DFS(nums, 0);
//     }
//     public boolean DFS(int[] nums, int cur) {
//         if (cur == nums.length - 1) { // reaches the last index
//             return true;
//         }
//         for (int newStart = Math.min(cur + nums[cur], nums.length - 1); newStart > cur; newStart--) {
//             if (DFS(nums, newStart)) {
//                 return true;
//             }
//         }
//         return false;
//     }
// }

// My First Solution, beats 15%
// 朴素方法

// class Solution {
//     public boolean canJump(int[] nums) {
//         boolean[] canReach = new boolean[nums.length];
//         canReach[0] = true;
//         for (int i = 0; i < nums.length - 1; i++) {
//             int maxLen = nums[i];
//             for (int j = i + 1; j <= Math.min(i + maxLen, nums.length - 1); j++) {
//                 canReach[j] |= canReach[i];
//             }
//         }
//         return canReach[nums.length - 1];
//     }
// }