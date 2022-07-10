class Solution {
    public int arraySign(int[] nums) {
        int countNeg = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return 0;
            } else if (nums[i] < 0) {
                countNeg++;
            }
        }
        return countNeg % 2 == 0 ? 1 : -1;
    }
}