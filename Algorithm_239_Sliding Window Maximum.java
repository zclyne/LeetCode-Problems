import java.util.ArrayDeque;
import java.util.Deque;

// 解法1：双向队列
// 双向队列queue记录所有下标在范围[i - k + 1, i]之间的小于等于nums[i]的元素下标
// 由于插入队列时总是插入在尾端，所以越靠近队列头部，下标越小
// 每一轮循环中，先判断第一个元素是否在范围[i - k + 1, i]中，若不在，则直接删除
// 再从队列尾部把下标在nums中对应元素小于nums[i]的全部删除，因为这些下标从现在开始不可能是任何一个
// 滑窗中的最大元素对应的下标，无论滑窗是否包含下标i
// 此时，队列中所有下标在nums中对应的元素都比nums[i]更大，因此把i添加到队列尾部
// https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // if the first element is out of the range [i - k + 1, i], remove it
            if (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }
            // remove all the elements that are less than nums[i] from the end of the queue
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // now, all the elements in queue are greater than nums[i], otherwise queue is empty
            // just insert i to the end of the queue
            queue.offerLast(i);
            // i >= k - 1 means the window has reached its specified size now
            if (i >= k - 1) {
                result[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return result;
    }
}

// 解法2
// https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array

class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] max_left = new int[nums.length];
        int[] max_right = new int[nums.length];
    
        max_left[0] = nums[0];
        max_right[nums.length - 1] = nums[nums.length - 1];
    
        for (int i = 1; i < nums.length; i++) {
            max_left[i] = (i % k == 0) ? nums[i] : Math.max(max_left[i - 1], nums[i]);
    
            int j = nums.length - i - 1;
            max_right[j] = (j % k == 0) ? nums[j] : Math.max(max_right[j + 1], nums[j]);
        }
    
        int[] sliding_max = new int[nums.length - k + 1];
        for (int i = 0, j = 0; i + k <= nums.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + k - 1]);
        }
    
        return sliding_max;
    }
}