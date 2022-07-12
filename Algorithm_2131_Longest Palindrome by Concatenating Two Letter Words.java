import java.util.*;

// 如果一个word的两个字母相同，称其为special word
// 对于其他的normal word，如果一个word颠倒过来等于另一个word，则这两个word
// 可以在一个palindrome中被互相抵消，因此对palindrome的长度贡献为4
// 对于special word，不仅可以取2个word并抵消，还可以单独取1个放在整个palindrome的最中间
// 因此可以再贡献2的长度

class Solution {
    public int longestPalindrome(String[] words) {
        int[] countSpecialWords = new int[26];
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        
        for (String word : words) {
            if (word.charAt(0) != word.charAt(1)) { // normal word
                String reverse = new StringBuilder(word).reverse().toString();
                if (map.containsKey(reverse)) { // can form a pair
                    result += 4;
                    int countReverse = map.get(reverse);
                    if (countReverse > 1) { // still have remaining reverse words
                        map.put(reverse, countReverse - 1);
                    } else {
                        map.remove(reverse);
                    }
                } else {
                    // add word to map
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            } else { // special word
                countSpecialWords[word.charAt(0) - 'a']++;
            }
        }

        // for special words, can use one in the very middle of the palindrome
        // and can also form pairs just like normal words
        boolean selectedMiddle = false;
        for (int i = 0; i < 26; i++) {
            result += 4 * (countSpecialWords[i] / 2);
            if (countSpecialWords[i] % 2 == 1 && !selectedMiddle) { // can use one special word in the middle
                result += 2;
                selectedMiddle = true;
            }
        }

        return result;
    }
}