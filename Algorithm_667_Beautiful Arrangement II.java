// 思路：按 1, k + 1, 2, k, 3, k - 1 ...的顺序填充res的前半部分，该部分保证了相邻元素之差的最大值为k，且插值不断递减直至1
// 因此，当第一部分结束时，最后一个元素应该为 k / 2
// 若此时1 ~ n中仍有元素没有用完，这些元素一定 >= k + 2，,因此从k + 2开始，每次 + 1，依次添加到res的尾部
// 因为第一部分的结尾元素为 k / 2，而第二部分的起始元素为k + 2，因此这两个元素之差的绝对值不会超过k
// 而第二部分本身 k + 2, k + 3, k + 4, ... 相邻元素之差的绝对值恒为1
// 因此最终得到的res满足题目要求

class Solution {
    public int[] constructArray(int n, int k) {
        int low = 1, high = k + 1, pos = 0;
        int[] res = new int[n];
        boolean flag = true;
        while (low <= high)
        {
            if (flag) res[pos++] = low++;
            else res[pos++] = high--;
            flag = !flag;
        }
        while (pos < n)
        {
            res[pos++] = k + 2;
            k++;
        }
        return res;
    }
}