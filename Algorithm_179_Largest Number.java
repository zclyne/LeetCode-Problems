import java.util.Arrays;

// 思路：把nums中的int全部转换为String，然后排序，排序规则为对于两字符串s1、s2，若s1 + s2 > s2 + s1，则s1 + s2排在前面
// 例如s1 = "85", s2 = "9", s1 + s2 = "829", s2 + s1 = "985", 所以s2 + s1排在s1 + s2前面
// 排序后，把所有String按顺序连接起来，就是所要求的结果

class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsStr, (s1, s2) -> (s2 + s1).compareTo(s1 + s2)); // sort by descending order
        if (numsStr[0].charAt(0) == '0') { // the largest num in nums is 0, then return "0"
            return "0";
        }
        for (String num : numsStr) {
            res.append(num);
        }
        return res.toString();
    }
}