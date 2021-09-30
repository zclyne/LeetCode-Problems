import java.util.*;

// 方法：对于strings中的每一个字符串s，计算其相邻两字符之差，如果是负数，就加上26将其变为正数
// 然后把两两之差组成字符串，作为map的key。属于同一组的字符串，这个key必然相同

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strings) {
            String keyStr = getKeyStr(s);
            List<String> list = map.getOrDefault(keyStr, new ArrayList<>());
            list.add(s);
            map.put(keyStr, list);
        }
        
        List<List<String>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }

    private String getKeyStr(String s) {
        String result = "";
        for (int i = 0; i < s.length() - 1; i++) {
            int diff = s.charAt(i + 1) - s.charAt(i);
            if (diff < 0) {
                diff += 26;
            }
            result += "," + diff;
        }
        return result;
    }
}