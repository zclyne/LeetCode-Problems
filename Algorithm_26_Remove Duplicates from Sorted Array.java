// 思路：用变量count记录已经出现过发生重复的次数
// 例如[1, 1, 3]，count = 1。发生重复的次数是1，而重复元素的个数是2，不是同一个概念
// 若nums[i] == nums[i - 1]，则出现重复元素，count++
// 否则，把nums[i]移动到前面，由于当前是第i + 1个元素，而重复元素的个数为count
// 因此不重复的元素个数为i + 1 - count，所以应该 移动到下标是i - count的位置上

class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        return nums.length - count;
    }
}