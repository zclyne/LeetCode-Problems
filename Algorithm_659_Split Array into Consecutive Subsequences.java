import java.util.*;
import java.util.stream.IntStream;

// 方法1：greedy + heap
// 对于nums中某个元素nums[i]，有两种情况：
// 1. 将nums[i]加入到已有的某个subsequence末尾
// 2. 以nums[i]为起始，创建一个新的subsequence
// 并且还要保证每个subsequence的长度大于等于3
// 由于nums是有序的，所以如果nums[i]不能添加到任何一个已有的subsequence末尾
// 则nums[i]之后的任何元素也不能添加到已有的subsequence末尾
// 所以如果必须选择情况2，则要注意目前的所有subsequence中是否还有长度小于3的
// 如果有，就可以直接返回false
// 对于情况1，如果nums[i]可以加到某个subsequence中，则这个subsequence必须以
// nums[i] - 1结尾。可以用heap来快速查找subsequence
// 如果有多个subsequence都以nums[i] - 1结尾，则直观上应该把nums[i]添加到
// 长度最短的那个subsequence上，从而尽量使得所有subsequence的长度都大于等于3
// 对于所有末尾元素小于nums[i] - 1的subsequence，它们都已经不可能再继续增长
// 可以直接从heap中移除
// 如果遇到subsequence的末尾元素等于nums[i]的情况，无法把nums[i]加到这个subsequence上
// 并且所有之后的subsequence末尾元素都不会小于nums[i]（因为nums本身有序）
// 因此需要以nums[i]为start，创建新的subsequence，加入到heap中
// 最后，再次判断heap所剩的所有subsequence长度是否都不小于3
// 时间复杂度O(nlogn)

class Solution {
    public boolean isPossible(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // if the last element is equal, sort in increasing order of length
            // length = end - start + 1, so sort in decreasing order of start
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            // sort in increasing order of end
            return a[1] - b[1];
        });

        for (int cur : nums) {
            // look for the first sequence with cur <= top[1] + 1
            while (!pq.isEmpty() && cur > pq.peek()[1] + 1) {
                int[] top = pq.poll();
                int len = top[1] - top[0] + 1;
                if (len < 3) {
                    return false;
                }
            }
            if (pq.isEmpty() || cur == pq.peek()[1]) {
                // must create a new subsequence with cur as the start
                pq.offer(new int[]{cur, cur});
            } else {
                int[] top = pq.poll();
                // cur == top[1] + 1
                // since sequence already sorted in increasing order of len
                // top corresponds to the shortest subsequence ending with top[1]
                // append cur to this subsequence
                top[1] = cur;
                pq.offer(top);
            }
        }

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int len = top[1] - top[0] + 1;
            if (len < 3) {
                return false;
            }
        }

        return true;
    }
}

// 方法2：greedy + map
// 创建两个map。numsFreq记录nums中每个num剩余可用的次数
// seqsFreq记录当前每个subsequence的末尾元素到以这个元素结尾的subsequence的个数的映射
// 遍历nums，对于每个num，尝试以下两个操作
// 1. 将其加入到现有的subsequence中，要求存在以num - 1结尾的subsequence
// 2. 以num作为起始元素，创建一个新的subsequence，要求仍有可用的num + 1和num + 2
// 由于2已经判断了num + 1和num + 2，所以所有已有的subsequence长度都至少为3
// 不需要再额外判断长度条件
// 时间复杂度O(n)

class Solution2 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> numsFreq = new HashMap<>();
        Map<Integer, Integer> seqsFreq = new HashMap<>();

        for (int num : nums) {
            numsFreq.put(num, numsFreq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (numsFreq.get(num) == 0) {
                // num is already part of a valid subsequence
                continue;
            }
            if (seqsFreq.getOrDefault(num - 1, 0) > 0) {
                // can append num to an existing subsequence
                seqsFreq.put(num - 1, seqsFreq.get(num - 1) - 1);
                seqsFreq.put(num, seqsFreq.getOrDefault(num, 0) + 1);
            } else {
                // use num as the start of a new subsequence
                // need to check whether we have num + 1 and num + 2 left in numsFreq
                if (numsFreq.getOrDefault(num + 1, 0) > 0 &&
                    numsFreq.getOrDefault(num + 2, 0) > 0) {
                    numsFreq.put(num + 1, numsFreq.get(num + 1) - 1);
                    numsFreq.put(num + 2, numsFreq.get(num + 2) - 1);
                    seqsFreq.put(num + 2, seqsFreq.getOrDefault(num + 2, 0) + 1);
                } else {
                    return false;
                }
            }
            numsFreq.put(num, numsFreq.get(num) - 1);
        }
        return true;
    }
}