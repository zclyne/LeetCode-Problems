import java.util.Random;

// 思路：各种排序算法

// QuickSort
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int pivotIndex = selectPivot(nums, left, right);
        swap(nums, left, pivotIndex); // put pivot in the first index
        int mid = partition(nums, left, right);
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    private int selectPivot(int[] nums, int left, int right) {
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        return pivotIndex;
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (nums[j] > pivot && i < j) {
                j--;
            }
            while (nums[i] <= pivot && i < j) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// heap sort
class HeapSolution {
    public int[] sortArray(int[] nums) {
        buildHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            // swap the root of the heap with the last node
            swap(nums, 0, i);
            // make sure the heap is still valid
            shiftDown(nums, 0, i);
        }
        return nums;
    }

    private void buildHeap(int[] nums) {
        // shift down from the first node that is not a leaf, whose index is (nums.length - 1) / 2
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            shiftDown(nums, i, nums.length);
        }
    }

    // shift down from the given starting point
    private void shiftDown(int[] nums, int start, int len) {
        int father = start;
        while (2 * father + 1 < len) {
            int child = 2 * father + 1, rightChild = 2 * father + 2;
            if (rightChild < len && nums[rightChild] > nums[child]) { // value of right child is greater than left child
                child = rightChild;
            }
            if (nums[father] < nums[child]) { // not satisfy the requirement of a max heap, needs swap
                swap(nums, father, child);
                father = child;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}