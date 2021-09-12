import java.util.Deque;
import java.util.LinkedList;

// 方法1：单调栈
// 从后向前遍历nums，并且维护一个单调递减的单调栈
// 用变量maxPopped记录从栈中被弹出的元素的最大值
// 在遍历过程中，如果当前元素的值小于maxPopped，说明找到了一个符合条件的132 pattern，返回true
// 具体分析如下：
// 由于栈中元素单调递减，因此只有当当前元素的值比栈顶元素更大时，栈顶元素才会被pop出来
// 这就保证了maxPopped一定小于栈中某个元素的值，因为它是在尝试向栈中添加这个元素时被pop出来的
// 并且由于遍历顺序是从后向前，所以这就代表了存在一对下标(j, k)，使得nums[j] > nums[k]
// 其中k对应的就是maxPopped
// 而若当前元素值小于maxPopped，说明找到了一个下标i，使得nums[i] < nums[k]
// 因此有nums[i] < nums[k] < nums[j]，即132 pattern
// https://leetcode-cn.com/problems/132-pattern/solution/xiang-xin-ke-xue-xi-lie-xiang-jie-wei-he-95gt/

class Solution {
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();

        int maxPopped = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int cur = nums[i];
            if (cur < maxPopped) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < cur) {
                maxPopped = Math.max(maxPopped, stack.pop());
            }
            stack.push(cur);
        }

        return false;
    }
}

// 方法2：TreeMap
// 枚举132中的3的下标j
// 对于i，只需要维护一个nums[0 ~ j - 1]范围内的最小值leftMin
// 对于k，需要用treeMap维护nums[j + 1 ~ n - 1]范围内的所有值
// 对于给定的leftMin，从treeMap中找到比leftMin大的最小值，并判断其是否小于nums[j]
// 若是，则说明找到了一个132 pattern，返回true

class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }

        return false;
    }
}