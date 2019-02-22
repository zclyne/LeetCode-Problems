// Min-heap Solution
// 思路：用小顶堆先存入nums的前k个元素，假设是最大的k个元素，随后对nums遍历，若当前遍历到的元素nums[i] > 堆顶元素，则把堆顶元素出堆，把nums[i]入堆
// 遍历结束后，堆顶元素就是第k大的元素

import java.util.PriorityQueue;
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // minimum heap
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
}

// Quickselect Algorithm Solution
// 思路：类似于快排，取一个pivot，并将数组分为两部分，左半部分值全部<=pivot，右半部分值全部>=pivot
// 根据pivot与k的位置比较，决定直接返回pivot还是向左半部分或右半部分递归

class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1); // kth maximum = nums.length - k + 1 th minimum
    }

    // quickSelect() returns the kth minimum num in the array
    public int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = nums[start];
        int left = start, right = end;

        // put every number which is less than pivot into the left half
        // and every number which is greater than pivot into the right half
        while (left < right) {
            while (nums[right] >= pivot && right > left) { // notice >=
                right--;
            }
            while (nums[left] <= pivot && left < right) { // notice <=
                left++;
            }
            if (left < right) { // swap
                swap(nums, left, right);
            }
        }
        // now, left is the correct place for pivot
        swap(nums, start, left);

        if (left == k - 1) {
            return nums[left];
        } else if (left > k - 1) { // target is in the left half
            return quickSelect(nums, start, left - 1, k);
        } else { // target is in the right half
            return quickSelect(nums, left + 1, end, k);
        }
    }

    public void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}