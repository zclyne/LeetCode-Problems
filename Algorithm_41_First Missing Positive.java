// 把数字放置到该数字对应的下标位置上
// 例如，若当前数字为5，就把当前数字与nums[4]交换
// 该算法的时间复杂度为O(n)，因为每次交换都保证了nums[nums[i] - 1]上存储到了正确的值
// 而数组中最多存在n个错误的配对情况，所以最多发生n次交换
// 第二遍循环检查元素nums[i]上存储的值是否正确，若不正确，表明i + 1没有在原数组中出现过

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}