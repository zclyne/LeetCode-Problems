import java.util.*;

// 对于每个word，先将其转换成morse code
// 然后把code都放入一个set中做去重

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            String code = wordToCode(word, codes);
            set.add(code);
        }
        return set.size();
    }
    private String wordToCode(String word, String[] codes) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            String temp = codes[c - 'a'];
            sb.append(temp);
        }
        return sb.toString();
    }
}