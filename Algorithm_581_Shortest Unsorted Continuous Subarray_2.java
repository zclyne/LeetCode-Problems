import java.util.Deque;
import java.util.LinkedList;

// 方法1：双指针
// 把数组nums看做nums1, nums2, nums3三个部分，其中nums1和nums3都有序，而将nums2排序后，nums整体有序
// 因此可以得到这样的性质：
// nums2, nums3中所有元素都比nums1中任意元素大
// nums1, nums2中所有元素都比nums3中任意元素小
// 也就是说，要找到nums2的左边界，就是要找到一个最小的下标i，使得nums[0 ~ i]中任意元素都小于nums[i + 1 ~ n - 1]中的最小值这一性质不成立
// 对于nums2的右边界，同理，找到一个最大的下标j，使得nums[j ~ n - 1]中任意元素都大于nums[0 ~ j - 1]中的最大值这一性质不成立
// 对于左边界的查找可以采用如下方法：
// 从右到左遍历nums，并维护一个已经遍历过的所有元素中的最小值rightMin
// 如果发现当前元素nums[i]大于这个rightMin，说明从下标i开始都是需要被排序的，令左边界left = i
// 否则，nums[i] <= rightMin, 更新rightMin = nums[i]
// 则遍历完成后，left中保存的就是nums2部分的左边界
// 同理，从左向右遍历nums，并维护一个已经遍历过的元素中的最大值leftMax
// 遍历完成后，right中保存的就是nums2部分的右边界
// 则right - left + 1即为答案

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = n, rightMin = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > rightMin) {
                left = i;
            } else {
                rightMin = nums[i];
            }
        }
        
        if (left == n) { // nums is already in order
            return 0;
        }

        int right = 0, leftMax = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < leftMax) {
                right = i;
            } else {
                leftMax = nums[i];
            }
        }

        return right - left + 1;
    }
}

// 方法2：单调栈
// 先从左向右遍历一遍，并且维护一个单调递增的单调栈
// 变量left记录nums中需要排序的部分的左边界，初始值为n
// 栈中存放的元素是一个int[]，存储nums中的元素值以及对应的下标
// 遍历过程中，若当前元素cur大于栈顶元素，则从栈中pop出一个元素
// 并判断被pop出来的元素的下标和left的关系，取较小者作为新的left的值
// 只有当一个元素的右侧出现了比它更小的元素时，这个元素才会被pop出来
// 因此一轮遍历结束后，left就记录了nums中无序部分的最左边界
// 同理，从右向左再遍历一遍nums，并维护一个单调递减的单调栈和变量right
// 则最终，right记录的是nums中无序部分的最右边界
// 则right - left + 1就是无序部分的长度

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = n, right = -1;
        Deque<int[]> increasingStack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            while (!increasingStack.isEmpty() && increasingStack.peek()[0] > cur) {
                int[] top = increasingStack.pop();
                left = Math.min(left, top[1]);
            }
            increasingStack.push(new int[]{cur, i});
        }

        Deque<int[]> decreasingStack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = nums[i];
            while (!decreasingStack.isEmpty() && decreasingStack.peek()[0] < cur) {
                int[] top = decreasingStack.pop();
                right = Math.max(right, top[1]);
            }
            decreasingStack.push(new int[]{cur, i});
        }

        if (left == n && right == -1) {
            return 0;
        }
        return right - left + 1;
    }
}

// 方法3：排序
// 将nums看做nums1, nums2, nums3三个部分
// 其中nums1, nums3都有序，nums2需要被排序
// 则可以直接对nums做一次拷贝，然后将拷贝后的结果从小到大排序
// 再与原本的nums作比较，最左边和最右边的值不同的位置就是nums2的起始和结束位置

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        int left = 0;
        while (left < n && nums[left] == numsCopy[left]) {
            left++;
        }
        if (left == n) { // nums is already in order
            return 0;
        }
        int right = n - 1;
        while (right >= 0 && nums[right] == numsCopy[right]) {
            right--;
        }
        return right - left + 1;
    }
}