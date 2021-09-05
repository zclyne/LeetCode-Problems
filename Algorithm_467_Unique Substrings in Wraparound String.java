import java.util.HashMap;
import java.util.Map;

// 方法：类似于前缀数组的思想
// 遍历p，用变量len存储当前的substring的长度。判断当前字符和上一个字符的差是否正好为1或-25（-25表示上一个字符为z，当前字符为a）
// 若是，则len++；若不是，则len = 1
// 然后以当前字符curChar为key，判断map中原本的value和当前len哪个更大，并将二者中较大的那个作为新的value存入map
// 因此map中保存的就是，以各个字符为结尾的最长的substring的长度
// 最后，将map中的所有value相加，就是答案
// 这样的方法不会有重复计算。因为以同一个字符结尾的较短的substring一定已经被包含在了较长的substring中了
// 例如，取p = abcazabc
// 遍历完成后，map中的各个值分别为c: 4, b: 3, a: 2, z: 1
// 以c为例。4代表了c, bc, abc, zabc这四个substring
// 所以result += 4，就将以c结尾的所有满足条件的substring都加到了最终的结果里

class Solution {
    public int findSubstringInWraproundString(String p) {
        Map<Character, Integer> map = new HashMap<>();
        p = "^" + p; // in order to make it easy to handle the 1st char
        int len = 1;
        for (int i = 1; i < p.length(); i++) {
            char curChar = p.charAt(i), lastChar = p.charAt(i - 1);
            if (curChar - lastChar == 1 || curChar - lastChar == -25) { // can form a longer substring
                len++;
            } else {
                len = 1;
            }
            map.put(curChar, Math.max(map.getOrDefault(curChar, 0), len));
        }
        int result = 0;
        for (int num : map.values()) {
            result += num;
        }
        return result;
    }
}