import java.util.HashMap;


class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            Character curChar = s.charAt(i), nextChar = s.charAt(i + 1);
            if (map.get(curChar) < map.get(nextChar)) {
                res -= map.get(curChar);
            } else {
                res += map.get(curChar);
            }
        }
        return res + map.get(s.charAt(s.length() - 1));
    }
}