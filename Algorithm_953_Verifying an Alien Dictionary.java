// 方法：用orderOfLetters来存储新的order到具体字母在order中的下标的映射，也就是字母间的相对关系
// compare方法用于判断字符串s1和s2的大小关系。如果s1 < s2则返回-1，s1 == s2则返回0，s1 > s2则返回1
// 遍历一遍words，两两比较，若出现words[i] > words[i + 1]，则说明不是lexicographical order，返回false
// 否则，返回true

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderOfLetters = new int[26];
        for (int i = 0; i < 26; i++) {
            char c = order.charAt(i);
            orderOfLetters[c - 'a'] = i;
        }
        int n = words.length;
        for (int i = 0; i < n - 1; i++) {
            if (compare(words[i], words[i + 1], orderOfLetters) > 0) {
                return false;
            }
        }
        return true;
    }

    private int compare(String s1, String s2, int[] orderOfLetters) {
        int i = 0;
        while (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        if (i == s1.length() && i == s2.length()) {
            return 0;
        } else if (i == s1.length()) { // length of s1 is shorter
            return -1;
        } else if (i == s2.length()) { // length of s2 is shorter
            return 1;
        } else {
            return orderOfLetters[s1.charAt(i) - 'a'] - orderOfLetters[s2.charAt(i) - 'a'];
        }
    }
}