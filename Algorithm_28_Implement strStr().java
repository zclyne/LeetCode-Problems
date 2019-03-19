// Brute Force Solution
// 思路：直接从s中的每一个字符开始比较

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (compare(haystack, needle, i)) {
                return i;
            }
        }
        return -1;
    }
    private boolean compare(String s, String t, int startIdx) {
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(startIdx + i) != t.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}



// Elegant Brute Force Solution

class ElegantSolution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
          for (int j = 0; ; j++) {
            if (j == needle.length()) return i;
            if (i + j == haystack.length()) return -1;
            if (needle.charAt(j) != haystack.charAt(i + j)) break;
          }
        }
      }
}



// KMP Solution
// 思路：https://www.bilibili.com/video/av3246487?from=search&seid=8047564525522991191

class KMPSolution {

    private int[] next;

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        next = new int[needle.length()];
        getNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j;
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = next[j - 1];
                }
            }
        }
        return -1;
    }

    private void getNext(String needle) {
        int i = 1, j = 0;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(j)) { // match
                next[i++] = ++j;
            } else { // not match
                if (j == 0) {
                    i++;
                } else {
                    j = next[j - 1];
                }
            }
        }
    }

}