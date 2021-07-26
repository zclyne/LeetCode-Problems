// 方法：固定上下边界，二维转化为一维，则原问题转化成【在一维数组中，求解和不超过k的最大连续子数组之和】
// 对于这个一维问题，可以先求出前缀和sum，然后枚举左端点，通过二分的方法查找右端点来解决
// 但是这种方法只适用于数组中的值非负，前缀和数组单调递增的情况
// 此处数组中的值可能为负，因此只能采取另一种方法：枚举右端点，在遍历右端点的过程中，将前缀和存入一个有序集合
// 在有序集合中找到符合sum[j] - k <= sum[i] (i < j)条件的最小值sum[i]
// 这里的有序集合选择treeset
// https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/gong-shui-san-xie-you-hua-mei-ju-de-ji-b-dh8s/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0; // s存储这一行内的前缀和
                for (int v : sum) { // 等价于枚举右边界
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}