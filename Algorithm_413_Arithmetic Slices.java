// 思路：遍历数组A,找到每一个满足条件的最长slice，其长度记为count。若count >= 3，则该slice中的每一个subSlice都需要考虑在内
// 该slice包含的所有subSlice及其自身的总数量为(count - 2) * (count - 1) / 2。
// 例如当slice长度为5时，则包含长度为5的slice1个，长度为4的slice2个，长度为3的slice3个。所以1 + 2 + 3 = 6个，使用等差数列求和公式

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int diff = A[1] - A[0], count = 2, res = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i + 1] - A[i] == diff) count++;
            else {
                if (count >= 3) res += (count - 1) * (count - 2) / 2;
                diff = A[i + 1] - A[i];
                count = 2;
            }
        }
        if (count >= 3) res += (count - 1) * (count - 2) / 2; // handle the last slice
        return res;
    }
}