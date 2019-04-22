import java.util.Arrays;

// Sort Solution，O(nlogn)时间，O(n)空间
// 思路：先获得一个nums的拷贝，并将其从小到大排序
// 排序后，取出其中位数，根据题目要求，所有大于中位数的元素都放在奇数下标中
// 所有小于中位数的元素都放在偶数下标中

class Solution {
    public void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int left = (nums.length - 1) / 2; // index of the medium
        int right = nums.length - 1;
        int i = 0;
        
        while (i < nums.length) {
            if ((i & 1) == 1) { // i is odd
                nums[i++] = copy[right--];
            } else {
                nums[i++] = copy[left--];
            }
        }
    }
}

// Better Solution, O(n)时间，O(1)空间
// 思路：https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java

class BetterSolution {
    public void wiggleSort(int[] nums) {
        // int median = findKthLargest(nums, (nums.length + 1) / 2);
        // int n = nums.length;

        // int left = 0, i = 0, right = n - 1;

        // while (i <= right) {

        //     if (nums[newIndex(i,n)] > median) {
        //         swap(nums, newIndex(left++,n), newIndex(i++,n));
        //     }
        //     else if (nums[newIndex(i,n)] < median) {
        //         swap(nums, newIndex(right--,n), newIndex(i,n));
        //     }
        //     else {
        //         i++;
        //     }
        // }

        // easier-to-understand version
        int median = findKthLargest(nums,(nums.length+1)/2);
        int odd = 1;
        int even = nums.length % 2 == 0 ? nums.length - 2 : nums.length - 1;
        int[] tmpArr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > median){
                tmpArr[odd] = nums[i];
                odd += 2;
            } else if(nums[i] < median){
                tmpArr[even] = nums[i];
                even -= 2;
            }
        }
        while(odd < nums.length){
            tmpArr[odd] = median;
            odd += 2;
        }
        while(even >= 0){
            tmpArr[even] = median;
            even -= 2;
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = tmpArr[i];
        }

    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }

    private int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    // quickSelect() returns the kth minimum num in the array
    private int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = nums[start];
        int left = start, right = end;

        // put every number which is less than pivot into the left half
        // and every number which is greater than pivot into the right half
        while (left < right) {
            while (nums[right] >= pivot && right > left) {
                right--;
            }
            while (nums[left] <= pivot && left < right) {
                left++;
            }
            swap(nums, left, right);
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

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

}