// 思路：现将数组从小到大排序后，用i降序索引len - 1 ~ 2的元素
// 同时，用l和r对下标小于i的数组进行遍历，若一组l、r、i满足条件
// 则count += r - l，这是由于当现在的l满足条件时，任何大于l
// 小于r的下标都满足条件
class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--)
        {
            int l = 0, r = i - 1;
            while (l < r)
            {
                if (nums[l] + nums[r] > nums[i]) // valid
                {
                    count += r - l;
                    r--;
                }
                else l++;
            }
        }
        return count;
    }
}