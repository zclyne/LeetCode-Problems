import java.util.HashMap;
import java.util.Map;

// 方法：用2个map charToWord和wordToChar来存储pattern中的字母和s中的单词的双向映射

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        char[] patternChars = pattern.toCharArray();

        if (words.length != patternChars.length) {
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char c = patternChars[i];
            String word = words[i];
            if (charToWord.containsKey(c) && wordToChar.containsKey(word)) {
                char mappedChar = wordToChar.get(word);
                String mappedWord = charToWord.get(c);
                if (c != mappedChar || !word.equals(mappedWord)) {
                    return false;
                }
            } else if (charToWord.containsKey(c) || wordToChar.containsKey(word)) {
                return false;
            } else {
                charToWord.put(c, word);
                wordToChar.put(word, c);
            }
        }

        return true;
    }
}