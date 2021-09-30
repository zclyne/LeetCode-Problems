import java.util.*;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int curPreSum = nums[0] % k;
        int lastPreSum = 0;
        for (int i = 1; i < nums.length; i++) {
            curPreSum = (curPreSum + nums[i]) % k;
            // set.contains(curPreSum) means that the subarray between the last index where the preSum == curPreSum and the current index
            // is the very subarray that we're looking for
            if (set.contains(curPreSum)) {
                return true;
            }
            lastPreSum = (lastPreSum + nums[i - 1]) % k;
            set.add(lastPreSum);
        }
        return false;
    }
}