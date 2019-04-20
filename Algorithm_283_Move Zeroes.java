// 思路：变量insertIdx记录下一个要插入的非零元素的位置
// 遍历nums，当遇到一个非零元素时，就把该元素放到nums[insertIdx]上，并把insertIdx++
// 然后把当前位置元素置为0，注意要先判断insertIdx - 1与i是否相等，若相等，则置0会覆盖之前的插入

class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int insertIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertIdx++] = nums[i];
                if (insertIdx - 1 != i) {
                    nums[i] = 0;
                }
            }
        }
    }
}


// My First Solution, beats 15.46%

class MySolution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int j = i + 1;
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                if (j == nums.length) {
                    return;
                }
                swap(nums, i, j);
            }
        }

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}