// 将s分成前后两个部分s1和s2。s = s1 + s2
// s1是s的前缀的最长回文字符串，剩余部分为s2
// 将s2的反序添加在s的头部之后，就能够得到所要找的最短回文字符串
// 所以这道题本质上是找s的最长的前缀s1，使得s1是一个回文字符串

// 方法1：Rabin-Karp字符串哈希算法
// 将字符串看成一个base进制的数，它对应的十进制就是哈希值。两个字符串的哈希值相等，当且仅当两个字符串本身相同
// 若字符串很长时，其十进制无法用int保存，因此要对一个大质数mod取模。此时：
// 1. 如果两个字符串的哈希值在取模后不相等，那么这两个字符串本身一定不相同；
// 2. 如果两个字符串的哈希值在取模后相等，并不能代表这两个字符串本身一定相同。
// 尝试各种不同的base和mod，使之不会对测试数据产生哈希碰撞
// 一个字符串是回文串，当且仅当该字符串与它的反序相同。因此，我们仍然暴力地枚举s1的结束位置，并计算s1与s1的反序的哈希值。
// 如果这两个哈希值相等，说明我们找到了一个s的前缀回文串。

// https://leetcode-cn.com/problems/shortest-palindrome/solution/zui-duan-hui-wen-chuan-by-leetcode-solution/

class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        int base = 131, mod = 1000000007;
        int left = 0, right = 0, mul = 1;
        int best = -1;
        for (int i = 0; i < n; ++i) {
            left = (int) (((long) left * base + s.charAt(i)) % mod);
            right = (int) ((right + (long) mul * s.charAt(i)) % mod);
            if (left == right) {
                best = i;
            }
            mul = (int) ((long) mul * base % mod);
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }
}



// 方法2：KMP算法
// https://leetcode-cn.com/problems/shortest-palindrome/solution/zui-duan-hui-wen-chuan-by-leetcode-solution/

class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();

        // build the fail arr
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }
}