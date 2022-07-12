import java.util.*;

// 本质上是partition problem。将数组partition成所有元素之和相等的4份
// 是NP-hard问题，但是可以优化
// 类似于第698题

// 优化的DFS
// 先将matchsticks数组从大到小排序，再进入DFS
// 原因是我们总是先尝试把每个火柴放到第一个group
// 如果把某个火柴放入某个group时最终DFS结果为false
// 则用一个更长的火柴代替当前火柴，最终DFS结果一定仍然是false
// 所以把长的火柴放在前面能够更快地到达negative的结果
// 此外，对于重复的情况，不需要重新计算DFS，例如若当前4个group的所有元素之和sums为[5, 7, 5, 9]
// 如果把当前火柴加到第一个group的5上并DFS后返回false，就没有必要再把当前火柴加到第三个group的5上
// 重复计算

class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }
        if (matchsticks.length < 4 || sum % 4 != 0) {
            return false;
        }

        // sort matchsticks in descending order
        Arrays.sort(matchsticks);
        reverse(matchsticks);
        return DFS(matchsticks, 0, new int[4], sum / 4);
    }
    
    private boolean DFS(int[] matchsticks, int index, int[] sums, int target) {
        if (index == matchsticks.length) {
            return sums[0] == target && sums[1] == target && sums[2] == target;
        }
        // try adding the current stick to each group
        for (int i = 0; i < 4; i++) {
            if (sums[i] + matchsticks[index] > target) {
                continue;
            }
            // avoid duplicate calculation
            int j = i - 1;
            while (j >= 0) {
                if (sums[j] == sums[i]) { // has considered this case before
                    break;
                }
                j--;
            }
            if (j >= 0) {
                continue;
            }

            sums[i] += matchsticks[index];
            if (DFS(matchsticks, index + 1, sums, target)) {
                return true;
            }
            sums[i] -= matchsticks[index];
        }
        return false;
    }

    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}

// Brute DFS, TLE
// 用数组sums记录4个group各自的所有元素之和，当matchsticks遍历完时
// 若所有group之和都等于target(sum / 4)，说明可以partition，返回true
// 对于matchsticks中的任意一个元素，分别尝试将其放入4个group中的1个
// 然后向更深一层递归

class BruteDFSSolution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }
        if (matchsticks.length < 4 || sum % 4 != 0) {
            return false;
        }

        return DFS(matchsticks, 0, new int[4], sum / 4);

    }
    
    private boolean DFS(int[] matchsticks, int index, int[] sums, int target) {
        if (index == matchsticks.length) {
            return sums[0] == target && sums[1] == target && sums[2] == target;
        }
        // try adding the current stick to each group
        for (int i = 0; i < 4; i++) {
            if (sums[i] + matchsticks[index] > target) {
                continue;
            }
            sums[i] += matchsticks[index];
            if (DFS(matchsticks, index + 1, sums, target)) {
                return true;
            }
            sums[i] -= matchsticks[index];
        }
        return false;
    }
}