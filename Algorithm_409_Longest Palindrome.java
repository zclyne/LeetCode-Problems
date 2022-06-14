// 方法：遍历s，计算各个字符的出现次数，小写和大写字母分别存储在数组countLower和countUpper中
// 然后遍历countLower和countUpper。如果当前count是偶数，则可以加在palindrome的左右两端，所以全部都可以被使用到
// 所以result += count
// 如果当前count是奇数，则只能把count - 1个放在palindrome的左右两端，所以result += count - 1
// 两种情况合并，就是result += (count / 2) * 2
// 如果count是奇数，把变量hasOdd置为true
// 遍历结束后，如果hasOdd是true，说明存在count为奇数的字母，可以把一个这样的字母放到整个palindrome的中间
// 所以result++
// 最后result就是答案

class Solution {
    public int longestPalindrome(String s) {
        int[] countLower = new int[26];
        int[] countUpper = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                countLower[c - 'a']++;
            } else {
                countUpper[c - 'A']++;
            }
        }
        int result = 0;
        boolean hasOdd = false;
        for (int count : countLower) {
            result += (count / 2) * 2; // （count / 2) * 2 equals count if count is even, and it equals count - 1 if count is odd
            hasOdd |= count % 2 == 1;
        }
        for (int count : countUpper) {
            result += (count / 2) * 2;
            hasOdd |= count % 2 == 1;
        }
        if (hasOdd) {
            result++; // can have 1 char in the middle of the palindrome
        }
        return result;
    }
}