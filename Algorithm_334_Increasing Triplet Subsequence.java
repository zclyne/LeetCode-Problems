// 从左到右遍历数组nums，用small和big分别记录遇到的最小的值和次小的值
// 若small和big都已经被更新过，而此时又遇到一个比big更大的值
// 表明成功找到了所要求的subsequence，返回true
// 注意在返回true时，可能small的值已经发生了改变，例如nums = [1, 3, 0, 5]
// 在遇到5时，small = 0, big = 3，但是程序的结果不会受到影响
// 这是由于在更新big时，small的值为1，是符合要求的

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= small) {
                small = nums[i];
            } else if (nums[i] <= big) { // nums[i] > small and <= big
                big = nums[i];
            } else { // successfully find a num that is >= small and big
                return true;
            }
        }
        return false;
    }
}