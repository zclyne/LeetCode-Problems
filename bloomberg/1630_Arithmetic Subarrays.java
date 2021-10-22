import java.util.*;

// 方法1：排序
// 对于每一个nums[l[i], ...m r[i]]子数组，将其排序后判断是否为arithmetic
// 如果是，就在结果中插入true，否则插入false

class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int left = l[i], right = r[i];
            int[] subArray = Arrays.copyOfRange(nums, left, right + 1);
            result.add(isArithmeticArray(subArray));
        }
        return result;
    }

    boolean isArithmeticArray(int[] nums) {
        Arrays.sort(nums);
        int diff = nums[1] - nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }
}

// 方法2
// 不用排序的方法来判断是否等差数列，而是采用如下办法：
// 遍历一遍当前的子数组，并记录其中的最大值和最小值
// 如果最大值和最小值相等，说明所有元素都相等，则直接返回true
// 否则，如果能构成等差数列，则说明(max - min) % (size - 1)必然等于0
// 若不满足以上条件，直接返回false
// 最后，以(max - min) / (size - 1)作为步长，min为起始值
// 判断当前数字num是否存在于set中（set保存了nums中所有数字）
// 一旦发现一个num不存在于set中，则原子数组无法构成等差数列，返回false
// 若遍历正常结束，则返回true

class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int left = l[i], right = r[i];
            int[] subArray = Arrays.copyOfRange(nums, left, right + 1);
            result.add(isArithmeticArray(subArray));
        }
        return result;
    }

    boolean isArithmeticArray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }
        int size = nums.length;
        if (max == min) { // all elements are the same
            return true;
        }
        if ((max - min) % (size - 1) != 0) { // impossible to form an arithmetic array
            return false;
        }
        int diff = (max - min) / (size - 1);
        for (int num = min; num <= max; num += diff) {
            if (!set.contains(num)) {
                return false;
            }
        }
        return true;
    }
}