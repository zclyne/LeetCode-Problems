// 方法：注意到输入的字符串s中只包含字母a或b
// 如果s仅通过1次就能变成空字符串，说明s本身是回文字符串
// 若s本身不是回文，则可以先消除所有a、再消除所有b，所以2次就能将s变为空字符串

class Solution {
    public int removePalindromeSub(String s) {
        if (isPalindrome(s)) {
            return 1;
        }
        return 2;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}