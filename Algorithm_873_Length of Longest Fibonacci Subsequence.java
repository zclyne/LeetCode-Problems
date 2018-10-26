// 思路：首先将A中所有元素作为键、其下表作为值存入HashMap中
// 然后创建二维数组dp，其两个维度均为A的长度，dp[i][j]表示以A[i]、A[j]作为末尾2个元素的斐波那契子数列的最大长度
// 对数组A做遍历，记A[i] + A[j] = nextVal，如果nextVal也是A中的元素（nextValIndex是它在A中的下标）
// 则将dp[j][nextValIndex]置为dp[i][j] + 1，因为A[j]和nextVal成为了新的末尾2个元素
// 若此时dp[i][j] == 0，说明这3个数第一次开始构成斐波那契数列，因此设dp[j][nextValIndex]为3
// 每次对res做更新，在遍历结束后返回res

import java.util.HashMap;
import java.util.HashSet;

// solution 1, DP, O(N^2), beats 73.42%
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < A.length; i++) indices.put(A[i], i);
        for (int j = 0; j < A.length; j++) {
            for (int i = 0; i < j; i++) {
                int nextVal = A[i] + A[j];
                Integer nextValIndex = indices.get(nextVal);
                if (nextValIndex != null) {
                    dp[j][nextValIndex] = dp[i][j] == 0 ? 3 : dp[i][j] + 1;
                    res = Math.max(res, dp[j][nextValIndex]);
                }
            }
        }
        return res;
    }
}

// solution 2, O(N^2logN) beats 21.93%
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        HashSet<Integer> elements = new HashSet<>();
        int res = 2;
        for (int a : A) elements.add(a);
        for (int i = 0; i < A.length - 2; i++) {
            for (int j = i + 1; j < A.length - 1; j++) {
                int a = A[i], b = A[j];
                int nextVal = a + b, tmpLen = 2;
                while (elements.contains(nextVal)) {
                    a = b;
                    b = nextVal;
                    nextVal = a + b;
                    tmpLen++;
                }
                res = Math.max(res, tmpLen);
            }
        }
        return res == 2 ? 0 : res;
    }
}