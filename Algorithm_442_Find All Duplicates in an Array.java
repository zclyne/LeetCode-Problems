// 思路：令nums[i]为idx，将nums[idx - 1]上的值取负数。若该值原本就是负数，说明idx是duplicate，将其加入结果中
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++)
        {
            int idx = nums[i] > 0 ? nums[i] : -nums[i];
            if (nums[idx - 1] < 0) res.add(idx);// idx is duplicate
            else nums[idx - 1] = -nums[idx - 1];
        }
        return res;
    }
}