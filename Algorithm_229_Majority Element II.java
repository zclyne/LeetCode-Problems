// 方法：摩尔投票法
// 简单版参考第169题
// 注意当num和cand1、cand2都不同时，要同时把count1和count2都 - 1，也就是说一个新的num要同时和cand1、cand2都抵消
// 考虑如下三种情况：
// 1. nums中有2个数出现次数都大于n / 3，记为a和b，则nums中除了a和b以外的数字全部和a、b抵消后，
//    a、b的count依然 > 0，所以cand1, cand2就是a、b
// 2. nums中只有一个数的出现次数大于n / 3，记为a，且a现在放在cand1上。
//    由于cand2上的数的总出现次数比cand1少，count2可能会先被抵消到0，并替换成新的数
//    假设count1先被抵消到0，最后a也会被换回来
// 3. nums中没有数出现次数大于n / 3，则不会通过第二个for循环的检查，不会被加入到res中

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // 创建返回值
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // 初始化两个候选人candidate，和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;

        // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第1个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第2个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            }
            else if (cand2 == num) { // else if避免了cand1 == cand2而被重复计算的情况
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            res.add(cand1);
        }
        if (count2 > nums.length / 3) {
            res.add(cand2);
        }

        return res;
    }
}