// 方法：分别由于只允许crease，不允许increase，所以只需要考虑nums[i]比左右两个元素都小的情况
// 只需要考虑最初的两个元素nums[0]和nums[1]，分别讨论nums[0] < nums[1], nums[0] > nums[1]
// 遍历时，i以2为步长递增

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        // nums[0] should be greater than nums[1]
        int move1 = getMove(nums, 1);
        // nums[0] should be less than nums[1]
        int move2 = getMove(nums, 0);

        return Math.min(move1, move2);
    }

    private int getMove(int[] nums, int start) {
        int move = 0;
        for (int i = start; i < nums.length; i += 2) {
            int left = i == 0 ? Integer.MAX_VALUE : nums[i - 1];
            int right = i == nums.length - 1 ? Integer.MAX_VALUE : nums[i + 1];
            int minNeighbor = Math.min(left, right);
            if (nums[i] >= minNeighbor) {
                move += nums[i] - minNeighbor + 1;
            }
        }
        return move;
    }
}