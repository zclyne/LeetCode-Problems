import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 思路：首先对数组按元素从小到大排序，用变量i确定三个数中的第一个数（即最小的数）的位置
// 对于子数组nums[i + 1 ~ nums.length - 1]，用2 sum的方法
// 指针low和High分别指向子数组的起始和终止位置，判断nums[i] + nums[low] + nums[high] == 0
// 若成立，则这三个数满足条件，加入结果中，若不满足，根据nums[low] + nums[high]与-nums[i]的比较
// 来得出要将low增大，还是把high减小
// 注意消除重复元素

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) { // avoid duplicate
                int low = i + 1, high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] + nums[i] == 0) { // valid
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low + 1] == nums[low]) { // avoid duplicate
                            low++;
                        }
                        while (low < high && nums[high - 1] == nums[high]) { // avoid duplicate
                            high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < 0 - nums[i]) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }
}