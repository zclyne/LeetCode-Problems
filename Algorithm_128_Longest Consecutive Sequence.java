import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

// 用map记录当前已知的sequence的长度，分别存储以左端点和右端点为key，长度为value
// 例如对于序列{1, 2, 3, 4, 5}，map.get(1) == map.get(5) == 5
// 遍历nums，对于当前数字num，如果num已经存在于map中，表明这个num不能用作sequence的
// 组成部分，因此直接跳过
// 如果不存在map中，则分别以num - 1和num + 1为key，从map中取value，记为left和right
// 其中，left是以num - 1为右端点向左延申的连续sequence的长度
// right是以num + 1为左端点向右延申的连续sequence的长度
// 这是因为map中不包含num，因此num必然是第一次出现
// 用left、right和num本身构成新的subsequence长度，并用该长度更新num - left, num + right两个
// 端点在map中的值，另外还要在map中设置以num本身设置一个entry，这是为了之后再次出现num时
// 能够知道num已经出现过，而不做重复处理

class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // it is important to check whether map contains num or not
            // if we ignore this step, duplicates may affect the result
            if (!map.containsKey(num)) {
                // get the max length of the left sequence and right sequence next to num
                // notice here that left can only be the length of a sequence extending from
                // num - 1 to a smaller number rather than extending to the right
                // this is because if the sequence extends to the right, num itself would exist
                // in the map, and the if statement woul not be satisfied
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                // the new length that includes the left sequence, the right sequence and num itself
                int sum = left + right + 1;
                map.put(num, sum); // important. mark num as visited
                res = Math.max(res, sum);
                // update the left endpoint and right endpoint with the new length
                map.put(num - left, sum);
                map.put(num + right, sum);
            }
        }
        return res;
    }
}

// Set Solution
// 思路：用nums构建出一个set，然后遍历set，对于任意一个n，若n - 1不存在于set中，则n是一个sequence的起点
// 令m = n + 1，n + 2，...，判断m是否存在于set里。直到第一个不存在于set中的m，m - n就是这个sequence的长度

class SetSolution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int best = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {  // only check for one direction
                int m = n + 1;
                while (set.contains(m)) {
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
}

// Union-Find Solution

public class UnionFindSolution {
    public int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i],i);
            if (map.containsKey(nums[i]+1)) { // nums[i] + 1 and nums[i] belong to the same union
                uf.union(i,map.get(nums[i]+1));
            }
            if (map.containsKey(nums[i]-1)) { // nums[i] - 1 and nums[i] belong to the same union
                uf.union(i,map.get(nums[i]-1));
            }
        }
        return uf.maxUnion();
    }

    private class UF {
        private int[] list;
        // initialization
        // the parent of an element is itself
        public UF(int n) {
            list = new int[n];
            for (int i = 0; i < n; i++) {
                list[i] = i;
            }
        }
        
        // look for the root
        // after this action has done, the parent of an element
        // is exactly its root, which can speed up subsequent root(i)
        private int root(int i) {
            while (i!=list[i]) {
                list[i] = list[list[i]];
                i = list[i];
            }
            return i;
        }
        
        public boolean connected(int i, int j) {
            return root(i) == root(j);
        }
        
        public void union(int p, int q) {
          int i = root(p);
          int j = root(q);
          list[i] = j;
        }
        
        // returns the maxium size of union
        public int maxUnion() { // O(n)
            int[] count = new int[list.length];
            int max = 0;
            for (int i = 0; i < list.length; i++) {
                count[root(i)]++;
                max = Math.max(max, count[root(i)]);
            }
            return max;
        }
    }
}