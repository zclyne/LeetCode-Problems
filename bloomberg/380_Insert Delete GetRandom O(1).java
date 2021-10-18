import java.util.*;

// hashmap + arrayList

class RandomizedSet {

    private List<Integer> nums;
    private Map<Integer, Integer> numToPos;
    private Random rand;

    public RandomizedSet() {
        this.nums = new ArrayList<>();
        this.numToPos = new HashMap<>();
        this.rand = new Random();
    }
    
    public boolean insert(int val) {
        if (numToPos.containsKey(val)) {
            return false;
        }
        int size = nums.size();
        nums.add(val);
        numToPos.put(val, size);
        return true;
    }
    
    public boolean remove(int val) {
        if (!numToPos.containsKey(val)) {
            return false;
        }
        int idx = this.numToPos.get(val);
        if (idx != this.nums.size() - 1) { // not the last element, swap
            int lastNum = this.nums.get(this.nums.size() - 1);
            this.nums.set(idx, lastNum);
            this.numToPos.put(lastNum, idx);
        }
        this.nums.remove(this.nums.size() - 1);
        this.numToPos.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randIdx = this.rand.nextInt(this.nums.size());
        return this.nums.get(randIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */