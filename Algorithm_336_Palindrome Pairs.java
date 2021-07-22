import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 方法：假设存在两个字符串s1、s2，使得s1 + s2是一个回文字符串，s1和s2的长度分别为len1和len2，则有如下三种情况：
// 1. len1 == len2，则s2是s1的一个翻转
// 2. len1 > len2，则可以把s1拆分成s1 = t1 + t2，使得t2是一个回文字符串，而t1是s2的翻转
// 3. len1 < len2，则可以把s2拆分成s2 = t1 + t2，使得t1是一个回文字符串，而t2是s1的翻转
// 因此，对于words中的每一个字符串，我们可以令它为s1和s2中较长的那一个，然后寻找可能与它构成回文串的字符串
// 我们枚举每一个字符串k，令k = t1 + t2，则有这样两种情况：
// 1. t1是回文串，则要从words中找到t2的翻转。若找到，则构成了满足条件的pair
// 2. t2是回文串，则要从words中找到t1的翻转。若找到，则构成了满足条件的apir
// 由于空串也是回文串，因此回文串长度为0时，就能够把len1 == len2的情况包含在内
// 字符串是否存在的判断使用字典树Trie来完成
// 也可以用HashMap来代替这里的字典树
// https://leetcode-cn.com/problems/palindrome-pairs/solution/hui-wen-dui-by-leetcode-solution/

class Solution {

    private class Trie {
        public int wordIndex; // wordIndex records the word's index in the array words
        public Trie[] children;
        public Trie() {
            wordIndex = -1; // -1 means word doesn't exist
            children = new Trie[26];
        }
    }

    private Trie root;

    // putWord puts a word into the trie and record the word's index
    private void putWord(String word, int index) {
        Trie cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new Trie();
            }
            cur = cur.children[idx];
        }
        cur.wordIndex = index;
    }

    // getWordIndex gets the word's index, and if the word doesn't exist, it returns -1
    private int getWordIndex(String word) {
        Trie cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            cur = cur.children[idx];
            if (cur == null) {
                return -1;
            }
        }
        return cur.wordIndex;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        // build the trie with words
        root = new Trie();
        for (int i = 0; i < words.length; i++) {
            putWord(words[i], i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // j can be equal to word.length() because word.length() might be 0
            for (int j = 0; j <= word.length(); j++) {
                // split word into word = t1 + t2
                String t1 = word.substring(0, j);
                String t2 = j == word.length() ? "" : word.substring(j);
                // j > 0 makes sure that if word.length() == target.length(), the pair won't be added twice
                if (j > 0 && isPalindrome(t1)) { // look for the reverse of t2 in the trie
                    int targetIdx = getWordIndex(new StringBuilder(t2).reverse().toString());
                    // notice that we need to check whether targetIdx == i, this is to avoid words with only 1 letter to be considered as the target
                    if (targetIdx != -1 && targetIdx != i) { // successfully found the reverse of t2
                        // words[targetIdx] + words[i] is palindrome
                        List<Integer> pair = new ArrayList<>();
                        result.add(Arrays.asList(targetIdx, i));
                    }
                }
                if (isPalindrome(t2)) { // look for the reverse of t1 in the trie
                    int targetIdx = getWordIndex(new StringBuilder(t1).reverse().toString());
                    // notice that we need to check whether targetIdx == i, this is to avoid words with only 1 letter to be considered as the target
                    if (targetIdx != -1 && targetIdx != i) { // successfully found the reverse of t1
                        // words[i] + words[targetIdx] is palindrome
                        List<Integer> pair = new ArrayList<>();
                        result.add(Arrays.asList(i, targetIdx));
                    }
                }
            }
        }

        return result;
    }

    // isPalindrome checks whether the given word is a palindrome
    private boolean isPalindrome(String word) {
        int left = 0, right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}