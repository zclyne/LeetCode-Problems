import java.util.HashMap;
import java.util.Map;

// use map to store the mapping from index to value where value is not 0

class SparseVector {

    private Map<Integer, Integer> map;
    
    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        if (vec.map.size() < this.map.size()) {
            return vec.dotProduct(this);
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int idx = entry.getKey();
            int value = entry.getValue();
            if (vec.map.containsKey(idx)) {
                result += value * vec.map.get(idx);
            }
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);