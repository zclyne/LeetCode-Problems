// 思路：当遇到一个nums[i] < nums[i - 1]时
// 优先考虑修改nums[i - 1]，这是因为修改增大nums[i]会增加后续逆序对数变多的风险
// 而减小nums[i - 1]的方法比较保险
// 但如果nums[i - 2] > nums[i]，我们就不得不修改nums[i]，将其增大到和nums[i - 1]相等
// 在每次操作结束后，都能够保证nums[0 ... i]是非递减序列

class Solution {
    public boolean checkPossibility(int[] nums) {
        int cnt = 0; //the number of changes
        for(int i = 1; i < nums.length && cnt <= 1 ; i++){
            if(nums[i - 1] > nums[i]){
                cnt++;
                if (i - 2 < 0 || nums[i - 2] <= nums[i]) { //modify nums[i-1] of a priority
                    nums[i - 1] = nums[i];
                }
                else nums[i] = nums[i - 1]; //have to modify nums[i]
            }
        }
        return cnt <= 1;
    }
}