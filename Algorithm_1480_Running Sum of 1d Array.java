class Solution {
    public int[] runningSum(int[] nums) {
        int last = 0;
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = last + nums[i];
            last += nums[i];
        }

        return res;
    }
}