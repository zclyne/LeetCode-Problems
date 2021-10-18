import java.util.*;

// 方法：注意到题目给出的数据范围，size(nums1) <= 1000，size(nums2) <= 10^5
// 所以在count时，遍历nums1比遍历nums2更好
// 用map存储nums2中元素到元素个数的映射

class FindSumPairs {

    private Map<Integer, Integer> nums2Count;
    private int[] nums1;
    private int[] nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.nums2Count = new HashMap<>();
        for (int num : nums2) {
            this.nums2Count.put(num, this.nums2Count.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        int originalVal = this.nums2[index];
        int newVal = originalVal + val;
        this.nums2[index] = newVal;
        this.nums2Count.put(originalVal, this.nums2Count.get(originalVal) - 1);
        this.nums2Count.put(newVal, this.nums2Count.getOrDefault(newVal, 0) + 1);
    }
    
    public int count(int tot) {
        int result = 0;
        for (int n : this.nums1) {
            result += this.nums2Count.getOrDefault(tot - n, 0);
        }
        return result;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */