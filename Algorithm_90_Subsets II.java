import java.util.ArrayList;
import java.util.Arrays;

// 解法1：迭代法
// 对于nums中的任意一个数，只有在这个数是第一次出现的情况下，才能够将其加入到新的subset中
// 并且只能添加一次
// 如果不是第一次出现，那么只能加入到已有的subset中，并且可以添加任意次
// 详细分析：https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            int dupCount = 0;
            // check whether nums[i] is duplicate, and get the number of duplicates
            while(((i+1) < nums.length) && nums[i+1] == nums[i]) {
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            // traverse subsets obtained from the last step
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<Integer>(result.get(j));
                // add a duplicate to each subset 
                for (int t = 0; t <= dupCount; t++) {
                    element.add(nums[i]);
                    result.add(new ArrayList<Integer>(element));
                }
            }
        }

        return result;
    }
}



// 解法2：回溯法

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            // skip if nums[i] equals nums[i - 1]
            // checking whether i > start is necessary
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}