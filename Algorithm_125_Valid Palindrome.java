// 思路：先把s中的所有大写字母全部转换为小写，然后用首尾两个指针不断比较最外层的两个字母或数字是否相等
// 若不相等，则不是回文，返回false
// 若相等，指针向内移动，继续比较，直到left和right相遇

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}