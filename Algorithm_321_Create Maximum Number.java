// 方法：单调栈
// 令k = x + y，则题目转化为分别从nums1和nums2中找到一个长度为x和y的subsequence，是的这两个subsequence所代表的数字分别最大
// 然后将这两个subsequence merge起来
// 本题讲解以及单调栈4道经典题：https://leetcode-cn.com/problems/create-maximum-number/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-7/

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        // x代表从nums1中选择的subsequence的长度
        for (int x = start; x <= end; x++) {
            int[] subsequence1 = maxSubsequence(nums1, x);
            int[] subsequence2 = maxSubsequence(nums2, k - x);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    // maxSubsequence方法从数组nums中选择出长度为k的一个subsequence，使得这个subsequence代表的数字最大
    public int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        int[] stack = new int[k];
        int toRemove = len - k; // toRemove记录剩余的要移除的digit的个数
        int top = -1; // top记录当前的栈顶下标
        for (int digit : nums) {
            while (top >= 0 && toRemove > 0 && digit > stack[top]) {
                top--;
                toRemove--;
            }
            if (top < k - 1) {
                stack[++top] = digit;
            } else {
                toRemove--;
            }
        }

        return stack;
    }

    // merge方法合并subsequence1和subsequence2
    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    // compare方法比较subsequence1以index1为起点，subsequence2以index2为起点所表示的2个subsequence的大小关系
    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }
}