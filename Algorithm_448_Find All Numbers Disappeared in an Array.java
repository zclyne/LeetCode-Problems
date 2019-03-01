import java.util.List;
import java.util.ArrayList;

// Better Solution
// 思路：设当前访问到的数字为nums[i]且为正，由于所有数字都在1~n之间，则nums[i] - 1在0 ~ n - 1之间，可作为nums的下标
// 把nums[nums[i] - 1]置为其相反数，用来标志nums[i]这个数出现过
// 若nums[i]本身为负数，说明此时的i + 1存在于nums中，但仍未考虑nums[-nums[i] - 1]，因此对nums[i]取绝对值后-1，其余步骤相同

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]) - 1;
            if (nums[num] > 0) {
                nums[num] = -nums[num];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}



// My Solution
// 思路：对于nums[i]，把nums[nums[i] - 1]置为1，并把原来的nums[nums[i] - 1]交换到nums[i]上，直至遇到nums[nums[i] - 1]已经是-1为止

class MySolution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == -1) {
                i++;
            } else {
                int nextIndex = nums[i] - 1;
                if (nums[nextIndex] != -1) {
                    nums[i] = nums[nextIndex];
                    nums[nextIndex] = -1;
                } else {
                    i++;
                }
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}