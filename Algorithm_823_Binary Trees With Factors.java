import java.util.*;

// 动态规划
// 由于每个node的值都是arr中的元素值，所以嵌套循环，每次将arr[i]的值作为当前的树的根节点值
// 然后遍历arr中所有小于arr[i]的元素，这一步可以通过提前将arr排序来加速，这样就只需要遍历j∈[0, i)区间内的arr[j]
// 将arr[j]作为左子树的根节点值，则右子树根节点值等于arr[i] / arr[j]，如果能整除并且结果是arr中的元素值
// 则将两个值对应的树的个数相乘后加到当前根节点的树的总数上，并放回map中
// 最终，map中所有value之和就是结果

class Solution {
    private final int MOD = 1000000007;
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr); // sort to facilitate traversing
        // stores the mapping from the root node value to the number of trees
        Map<Integer, Long> rootToCount = new HashMap<>();
        // initialization, the tree only has one node, the root itself
        for (int num : arr) {
            rootToCount.put(num, 1L);
        }
        for (int i = 0; i < arr.length; i++) {
            int rootVal = arr[i];
            for (int j = 0; j < i; j++) {
                int leftVal = arr[j];
                if (rootVal % leftVal != 0) { // cannot divide
                    continue;
                }
                int rightVal = rootVal / arr[j];
                if (!rootToCount.containsKey(rightVal)) { // rightVal is not an element in arr
                    continue;
                }
                long count = rootToCount.get(rootVal);
                long leftCount = rootToCount.get(leftVal), rightCount = rootToCount.get(rightVal);
                count = (count + leftCount * rightCount) % this.MOD; // combinations, so use left * right
                rootToCount.put(rootVal, count);
            }
        }
        long result = 0;
        for (long count : rootToCount.values()) {
            result = (result + count) % this.MOD;
        }
        return (int) result;
    }
}