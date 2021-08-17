import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 方法1：暴力法
// 在shuffle过程中，先把原本的nums存入一个list arr，然后用随机数不断生成范围在[0, arr.size() - 1]范围内的int作为下标，记为idx
// 将arr.get(idx)放入result，然后把idx上的元素从arr中移除
// 直到最终arr为空为止，返回result
// 时间复杂度为O(n^2)

class Solution {

    private int[] nums;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = nums.length;
        int[] result = new int[n];
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(nums[i]);
        }

        int i = 0;
        while (!arr.isEmpty()) {
            int idx = rand.nextInt(arr.size());
            result[i++] = arr.get(idx);
            arr.remove(idx);
        }

        return result;
    }
}



// 方法2：Fisher-Yates洗牌算法
// 本质上和暴力法很像，首先获取一个原数组nums的copy，记为result。然后当前下标i遍历区间[0, n)。其中n是nums的长度
// 每次随机生成一个范围在当前下标i和数组末尾元素下标n - 1之间的整数，作为随机选取的下标randIdx
// 然后交换result中randIdx和i位置上的数字
// 最后把当前下标i++。这样，在当前下标之前的所有数都是已经从原数组中选出的数，之后不会再被选择到

class Solution {

    private int[] nums;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = this.nums.length;
        int[] result = Arrays.copyOf(nums, n);

        for (int i = 0; i < n; i++) {
            // randIdx is within the interval [i, n - 1]
            int randIdx = i + rand.nextInt(n - i);
            swap(result, i, randIdx);
        }

        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}