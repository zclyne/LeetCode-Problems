import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// 思路：与Algorithm_380基本相同，唯一区别是map的value类型变成Set
// 从而支持duplicate

class RandomizedCollection {

    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> indiciesSet = map.getOrDefault(val, new HashSet<>());
        boolean returnVal = indiciesSet.isEmpty();
        list.add(val);
        indiciesSet.add(list.size() - 1);
        map.put(val, indiciesSet);
        return returnVal;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> indiciesSet = map.getOrDefault(val, new HashSet<>());
        if (indiciesSet.isEmpty()) {
            return false;
        }
        if (indiciesSet.contains(list.size() - 1)) {
            indiciesSet.remove(list.size() - 1);
            list.remove(list.size() - 1);
        } else {
            int index = indiciesSet.iterator().next();
            indiciesSet.remove(index);
            int lastNum = list.get(list.size() - 1);
            list.set(index, lastNum);
            map.get(lastNum).remove(list.size() - 1);
            map.get(lastNum).add(index);
            list.remove(list.size() - 1);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */