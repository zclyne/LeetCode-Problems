import java.util.*;
import java.util.stream.IntStream;

// 类似第473题
// 先对nums排序，用数组p记录每个group的所有元素之和，然后递归中从大到小遍历
// 递归结束时，idx == -1。如果此时每个group的所有元素之和都等于target
// 说明找到了这样的一个partition，返回true；否则返回false
// 另外，如果两个group的所有元素之和相同，并且已经递归过其中一个group并返回false
// 就没必要再尝试另一个group

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        int sum = IntStream.of(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        return possible(nums, sum / k, new int[k], nums.length - 1);
    }
    
    boolean possible(int[] nums, int target, int[] p, int idx) {
        if (idx == -1) {
            for (int s : p) 
                if (s != target) return false;
            return true;
        }
        int num = nums[idx];
        
        for (int i = 0; i < p.length; i++) {
            if (p[i] + num <= target) {
                if (i > 0 && p[i] == p[i - 1]) { // if we've already tested this case, skip
                    continue;
                }
                p[i] += num;
                if (possible(nums, target, p, idx - 1)) {
                    return true;
                }
                p[i] -= num;
            }
        }
        return false;
    }    
}