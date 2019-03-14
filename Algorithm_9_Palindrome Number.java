// My Solution
// 思路：把int转换成字符串后，从两端开始比较x_str[left]与x_str[right]是否相等，若不相等则一定不是回文，直接退出循环
// 若x_str的长度是奇数，那么结束条件是left == right；若长度是偶数，则结束条件是left ==right + 1
// 所以最终left >= right就是判断是否回文的结果


class MySolution {
    public boolean isPalindrome(int x) {
        String x_str = String.valueOf(x);
        int left = 0, right = x_str.length() - 1;
        while (left < right && x_str.charAt(left) == x_str.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}


// Better Solution
// 思路：如果x为负数，肯定不回文，直接返回false；若x == 0，则是回文，返回true；若x的个位为0，则一定不是回文，返回false
// 用rev记录x的一半的reverse，循环终止条件为x <= rev，此时rev已经保存了原来x中的一半的reverse
// 返回条件x == rev时，说明原来x的长度是偶数，此时rev和x应正好相等，即回文
// x == rev / 10时，说明原来的x的长度是奇数，循环终止时rev比x多一位，例如原本的x = 12321，循环结束后x = 12，rev = 123
// 此时要把x与rev / 10作比较，若相等，则是回文


class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        } else if (x % 10 == 0) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }
}