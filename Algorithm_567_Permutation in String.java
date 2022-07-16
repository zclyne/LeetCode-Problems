// 滑动窗口。用一个长度为26的数组先存下s1中每个字母的计数
// 然后用一个长度等于s1.length()的窗口在s2上滑动，每次把新加入的字母的count--
// 把移除的字母的count++。如果数组中所有元素都为0，说明此时s2的这个substring就是s1的
// 一个permutation，返回true

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] countChars = new int[26];
        for (char c : s1.toCharArray()) {
            countChars[c - 'a']++;
        }

        int left = 0, right = 0;
        // extend the sliding window to length s1.length()
        while (right < s1.length() - 1) {
            countChars[s2.charAt(right++) - 'a']--;
        }

        // slide the window
        while (right < s2.length()) {
            countChars[s2.charAt(right++) - 'a']--;
            if (isValid(countChars)) {
                return true;
            }
            countChars[s2.charAt(left++) - 'a']++;
        }

        return false;
    }

    private boolean isValid(int[] countChars) {
        for (int i = 0; i < 26; i++) {
            if (countChars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}