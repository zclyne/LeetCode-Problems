// 直觉上，每次需要将除了最大值以外的所有元素都+1
// 就等价于将最大的元素本身-1
// 所以move的次数就等于把nums中所有元素-1，直到该元素等于nums中最小值所需要的减的次数

class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int result = 0;
        for (int num : nums) {
            result += (num - min);
        }
        return result;
    }
}