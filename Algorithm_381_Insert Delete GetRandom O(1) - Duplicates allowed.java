import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

// 思路：为了能在O(1)时间内随机获得一个元素，可以联想到把所有元素放在一个list中，通过随机数获取一个index即可
// 再用一个Map<Integer, Set<Integer>> numToIndicies存储nums中的元素到元素在nums中的下标的映射
// 对于insert操作，直接将元素insert到nums末尾，然后将这个下标添加到numToIndicies中对应的set里
// 如果添加完后，set的size是1，说明这个val是新添加的val，返回true；否则返回false
// 对于remove操作，先从numToIndicies中获取val对应的下标set。如果这个set是null或者为空，说明val不存在，返回false
// 否则，通过iterator来从下标set indicies中获取一个下标，记为idxToRemove，是要移除的下标
// 将nums[idxToRemove]的值从原本的val修改为nums的最后一个元素的值lastNum，本质上相当于将idxToRemove和lastIndex上的值互换
// 然后从nums中删除最后一个元素nums[lastIdx]，并修改对应的两个set
// 将idxToRemove从val对应的下标set indicies中删除，
// 在lastNum对应的set中，先添加idxToRemove，再移除lastIdx。这样就能保证即使idxToRemove == lastIdx时，代码也能够正常工作

class RandomizedCollection {

    private Map<Integer, Set<Integer>> numToIndicies;
    private List<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        numToIndicies = new HashMap<>();
        nums = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        int idx = nums.size() - 1;
        Set<Integer> indicies = numToIndicies.getOrDefault(val, new HashSet<>());
        indicies.add(idx);
        numToIndicies.put(val, indicies);
        return indicies.size() == 1; // size == 1 means val doesn't exist before
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> indicies = numToIndicies.get(val);
        if (indicies == null || indicies.isEmpty()) { // val doesn't exist
            return false;
        }
        int idxToRemove = indicies.iterator().next();
        int lastIdx = nums.size() - 1;
        int lastNum = nums.get(lastIdx);
        // replace val at idxToRemove with the last number in nums
        nums.set(idxToRemove, lastNum);
        indicies.remove(idxToRemove);
        // add idxToRemove and then remove lastIdx is necessary
        // because idxToRemove may be equal to lastIdx
        numToIndicies.get(lastNum).add(idxToRemove);
        numToIndicies.get(lastNum).remove(lastIdx);
        nums.remove(lastIdx);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int randIdx = (int) (Math.random() * nums.size());
        return nums.get(randIdx);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */