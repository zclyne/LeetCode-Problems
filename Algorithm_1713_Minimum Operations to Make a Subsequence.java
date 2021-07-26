import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// 方法：这个问题等价于找target和arr的最长公共subsequence（LCS），设这个subsequence的长度为len
// 则最少的操作次数等于target.length - len
// 传统的解决LCS问题的时间复杂度是O(m * n)，在这个问题中会超时
// 注意到，由于target中的元素都是唯一的，因此target中的元素和下标能够形成一一映射关系
// 遍历arr，对于arr中的当前元素arr[i]，如果它存在于数组target中，就将其下标存入列表indicies
// 注意如果arr[i]不存在在target中，不能加入，否则会导致答案错误
// 当列表indicies构建完成后，这个问题就又转化成了找indicies的最长递增子序列（LIS）问题
// 而这个问题可以采用枚举+二分的方法来解决，具体方法可以参考LC 300
// 数组g的元素g[i]的值就是：长度为i的递增子序列的末尾元素的最小值
// https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-oj7yu/

class Solution {
    public int minOperations(int[] target, int[] arr) {
        // build the mapping from nums in target to index
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            numToIndex.put(target[i], i);
        }

        List<Integer> indicies = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) { // add to indicies only if arr[i] exists in target
            if (numToIndex.containsKey(arr[i])) {
                indicies.add(numToIndex.get(arr[i]));
            }
        }

        if (indicies.isEmpty()) { // every element in arr doesn't exist in target
            return target.length;
        }

        // look for the longest increasing subsequence (LIS) of indicies
        int len = 1;
        int[] g = new int[indicies.size() + 1]; // g[i] stores the ending value of the LIS of length == i
        g[1] = indicies.get(0);
        for (int i = 1; i < indicies.size(); i++) {
            if (indicies.get(i) > g[len]) { // can form a longer subsequence
                g[++len] = indicies.get(i);
            } else {
                // look for the index to update using binary search
                int left = 1, right = len;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (g[mid] >= indicies.get(i)) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                g[left] = indicies.get(i);
            }
        }

        return target.length - len;
    }   
}