import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 思路：用map存储已经出现过的所有DNA序列
// 对于第一次出现的DNA序列，在map中以这个序列为key，存储值为1，表示出现过，但还没有重复过
// 对于已经存在于map中的序列，判断其对应的value是否为1。若为1，表示已经出现过，所以重复了
// 将其加入result中，并修改对应的value为2，表示已经添加过了

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put(s.substring(0, 10), 1);
        for (int i = 1; i <= s.length() - 10; i++) {
            String curString = s.substring(i, i + 10);
            int val = map.getOrDefault(curString, 0);
            if (val == 1) { // repeated, but not added to result yet
                result.add(curString);
                map.put(curString, 2);
            } else if (val == 0) { // not repeated yet, add to map
                map.put(curString, 1);
            }
        }
        return result;
    }
}