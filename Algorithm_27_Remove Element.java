// My Solution
// 思路：倒序遍历数组，把所有与val相等的元素交换到数组的尾部，swapIdx记录需要交换到的位置
// 每交换一次，swapIdx - 1。最终swapIdx等于所要返回的长度 - 1

class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int swapIdx = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == val) { // need to be swaped
                swap(nums, i, swapIdx--);
            }
        }
        return swapIdx + 1;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}



// Better Solution
// 思路：把所有不等于elem的元素放到数组的头部

class BetterSolution {
    public int removeElement(int[] A, int elem) {
        int m = 0;    
        for(int i = 0; i < A.length; i++){
            
            if(A[i] != elem){
                A[m] = A[i];
                m++;
            }
        }
        
        return m;
     }
}