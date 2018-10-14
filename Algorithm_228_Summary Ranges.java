// 思路：直接按顺序遍历原数组，用2个变量来存储当前的range
// 不要忘记在循环结束后处理包含nums[len - 1]的range
class Solution {
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) return res;
        if (len == 1)
        {
            res.add(nums[0] + "");
            return res;
        }
        String tmpRes;
        int tmpMin = nums[0], tmpMax = nums[0];
        for (int i = 1; i < len; i++)
        {
            if (nums[i] == nums[i - 1] + 1) tmpMax++;
            else
            {
                if (tmpMax == tmpMin) res.add(tmpMin + "");
                else res.add(tmpMin + "->" + tmpMax);
                tmpMin = tmpMax = nums[i];
            }
        }
        // handle the last number
        if (tmpMax == tmpMin) res.add(tmpMin + "");
        else res.add(tmpMin + "->" + tmpMax);

        return res;
    }
}