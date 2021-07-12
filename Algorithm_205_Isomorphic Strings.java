import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 方法1：用map存储s中字符向t中字符的映射
// reversedMap存储t中字符向s中字符的映射
// 由于s和t中的字符都是ascii字符，所以可以用长度为256的的2个数组来代替map

class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reversedMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (reversedMap.containsKey(c2)) {
                char expectedC1 = reversedMap.get(c2);
                if (c1 != expectedC1) {
                    return false;
                }
            }
            if (map.containsKey(c1)) {
                char expectedC2 = map.get(c1);
                if (c2 != expectedC2) {
                    return false;
                }
            }
            map.put(c1, c2);
            reversedMap.put(c2, c1);
        }
        return true;
    }
}