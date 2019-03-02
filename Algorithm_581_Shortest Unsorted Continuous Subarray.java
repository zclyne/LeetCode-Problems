import java.util.Arrays;

// 一次遍历，O(n) Solution

// 思路：对nums从左向右遍历，找到最后一个nums[i]使得nums[i] < max(nums[0, i - 1])
// 若满足该条件，说明nums[i]失序，因此要包含在需要排序的subarray中。对于最后一个满足该条件的i，这个i就是需要排序的subarray的最后一个元素的下标
// 同理，从右向左遍历nums，找到最后一个nums[i]使得nums[i] > min(nums[i + 1, nums.length - 1])
// 若满足该条件，说明nums[i]失序，该nums[i]要包含在需要排序的subarray中。对于从右向左遍历时的最后一个i，这个i就是需要排序的subarray的第一个元素的下标

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int max = nums[0], min = nums[nums.length - 1], start = -1, end = -2;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[nums.length - i - 1]);
            if (nums[i] < max) {
                end = i;
            }
            if (nums[nums.length - i - 1] > min) {
                start = nums.length - i - 1;
            }
        }
        return end - start + 1;
    }
}



// Sort Solution

class SortSolution {
    public int findUnsortedSubarray(int[] nums) {
        int start = 0, end = nums.length - 1;
        int[] temp = nums.clone();
        Arrays.sort(temp);
        while (start < nums.length && temp[start] == nums[start]) {
            start++;
        }
        while (end > start && temp[end] == nums[end]) {
            end--;
        }
        return end - start + 1;
    }
}