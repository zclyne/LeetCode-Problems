// 思路：count存储以end结尾的所有valid的subarray数量
// 若A[end]满足>=L且<=R，则A[start : end]是valid的subarray
// 任何一个A[s, end] (start <= s <= end)也都是满足条件的subarray
// 这里总共有end - start + 1个subarray，因此count = end - start + 1, res += count
// 若A[end]<L，有两种情况：一种是以end结尾的所有subarray都不满足条件，此时有count = 0，res += count不产生影响
// 另一种是以end结尾的subarray中最大值满足>=L且<=R，此时要加入res的值也是count
// 因为这个subarray必须包含从start开始到当前end的这一段subarray中的最后一个满足>=L且<=R的元素
// 因此这些subarray的个数就是count
// 若A[end] > R，则以end结尾的任何一个subarray都不满足条件，清空count并把start置为end + 1

class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int start = 0, res = 0, count = 0;
        for (int end = 0; end < A.length; end++)
        {
            if (A[end] >= L && A[end] <= R) // current element is valid
            {
                count = end - start + 1;
                res += count;
            }
            else if (A[end] < L)
            {
                res += count;
            }
            else // A[end] > R
            {
                count = 0;
                start = end + 1;
            }
        }
        return res;
    }
}