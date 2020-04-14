import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 思路：用map存储val到这个val在list中的下标的映射
// 插入时，直接插入到数组的尾部，并在map中创建相应的entry
// 查询时，直接通过随机数产生一个在list的范围内的下标，并返回相应的值
// 删除时，先通过map获取val对应的下标位置
// 如果下标恰好为list.size() - 1，则直接把val从map和list中删除，都是O(1)
// 若不是list.size() - 1，则先把list中的最后一个元素交换到当前位置上
// 最后更新map中这个元素对应的entry，即改变相应的list中的下标
// 最后从list中删除最后一个元素，并从map中删除val对应的entry

class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        if (index != list.size() - 1) {
            map.put(list.get(list.size() - 1), index);
            list.set(index, list.get(list.size() - 1));
        }
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */