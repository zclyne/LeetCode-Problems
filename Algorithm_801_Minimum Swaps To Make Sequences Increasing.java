// 思路：对于一个位置i，只有交换或不交换两种情况，0 ~ i - 1必然递增
// 在i位置的两个数字确定的情况下，不需要在乎之前的数字是否交换，只要使得交换次数最少
// 因此共有4种搭配，即remain - remain, swap - swap, swap - remain, remain - swap
// 两个数组swap和remain分别表示在位置i上选择交换和不交换时交换的次数
// 若有A[i] > A[i - 1] && B[i] > B[i - 1]，表明remain - remain和swap - swap两种情况可以成立
// 若有A[i] > B[i - 1] && B[i] > A[i - 1]，表明swap - remain和remain - swap两种情况可以成立
// 最后返回remain[A.length - 1]和swap[A.length - 1]中较小的那个即为答案
// 本题可以优化为O(1)空间复杂度

import java.util.Arrays;
class Solution {
    public int minSwap(int[] A, int[] B) {
        int[] swap = new int[A.length];
        int[] remain = new int[A.length];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(remain, Integer.MAX_VALUE);
        remain[0] = 0;
        swap[0] = 1;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) { // remain - remain and swap - swap
                remain[i] = remain[i - 1];
                swap[i] = swap[i - 1] + 1;
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) { // swap - remain and remain - swap
                remain[i] = Math.min(remain[i], swap[i - 1]);
                swap[i] = Math.min(swap[i], remain[i - 1] + 1);
            }
        }

        return Math.min(remain[A.length - 1], swap[A.length - 1]);
    }
}