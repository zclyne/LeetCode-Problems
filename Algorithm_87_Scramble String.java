// 动态规划（区间DP）
// 区间长度大的状态值可以由区间长度小的状态值递推而来
// 我们可以得到状态定义f[i][j][len]：
// f[i][j][len]代表s1从i开始，s2从j开始，后面长度为len的字符是否能形成「扰乱字符串」（互为翻转）
// 由于本身我们在「记忆化搜索」里面就是从小到大枚举 lenlen，因此这里也需要先将len这层循环提前，确保我们转移f[i][j][len]时所需要的状态都已经被计算好


class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        boolean[][][] f = new boolean[n][n][n + 1];

        // 先处理长度为 1 的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j][1] = cs1[i] == cs2[j];
            }
        }

        // 再处理其余长度情况，长度作为最外层循环，确保长度更短的情况已经全部被处理完毕
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k < len; k++) {
                        boolean a = f[i][j][k] && f[i + k][j + k][len - k];
                        boolean b = f[i][j + len - k][k] && f[i + k][j][len - k];
                        if (a || b) {
                            f[i][j][len] = true;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}

// recursive solution, TLE
// 思路：首先判断s1和s2中是否包含相同的字符，若不是，则直接返回false
// 随后从头开始遍历，考虑两种情况：
// 1. s1[0:i]对应于s2[0:i]，即以整个字符串作为根节点的两个子节点没有进行交换
// 2. s1[0:i]对应于s2[s2.length() - i:s2.length()]，即以整个字符串作为根节点的两个子节点进行了交换
// 递归进行判断

class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (!check(s1, s2)) return false;
        int n = s1.length();
        for (int i = 1; i < n; i++) {
            // s1 的 [0,i) 和 [i,n)
            String a = s1.substring(0, i), b = s1.substring(i);
            // s2 的 [0,i) 和 [i,n)
            String c = s2.substring(0, i), d = s2.substring(i);
            if (isScramble(a, c) && isScramble(b, d)) return true;
            // s2 的 [0,n-i) 和 [n-i,n)
            String e = s2.substring(0, n - i), f = s2.substring(n - i);
            if (isScramble(a, f) && isScramble(b, e)) return true;
        }
        return false;
    }
    // 检查 s1 和 s2 词频是否相同
    boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        int[] cnt1 = new int[26], cnt2 = new int[26];
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        for (int i = 0; i < n; i++) {
            cnt1[cs1[i] - 'a']++;
            cnt2[cs2[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
}

// optimized recursive solution
// 使用数组避免了重复计算

class Solution {
    String s1; String s2;
    int n;
    int[][][] cache;
    int N = -1, Y = 1, EMPTY = 0;
    public boolean isScramble(String _s1, String _s2) {
        s1 = _s1; s2 = _s2;
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        n = s1.length();
        // cache 的默认值是 EMPTY
        cache = new int[n][n][n + 1];
        return dfs(0, 0, n);
    }
    boolean dfs(int i, int j, int len) {
        if (cache[i][j][len] != EMPTY) return cache[i][j][len] == Y;
        String a = s1.substring(i, i + len), b = s2.substring(j, j + len);
        if (a.equals(b)) {
            cache[i][j][len] = Y;
            return true;
        } 
        if (!check(a, b)) {
            cache[i][j][len] = N;
            return false;
        } 
        for (int k = 1; k < len; k++) {
            // 对应了「s1 的 [0,i) & [i,n)」匹配「s2 的 [0,i) & [i,n)」
            if (dfs(i, j, k) && dfs(i + k, j + k, len - k)) {
                cache[i][j][len] = Y;
                return true;
            }
            // 对应了「s1 的 [0,i) & [i,n)」匹配「s2 的 [n-i,n) & [0,n-i)」
            if (dfs(i, j + len - k, k) && dfs(i + k, j, len - k)) {
                cache[i][j][len] = Y;
                return true;
            }
        }
        cache[i][j][len] = N;
        return false;
    }
    // 检查 s1 和 s2 词频是否相同
    boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        int[] cnt1 = new int[26], cnt2 = new int[26];
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        for (int i = 0; i < n; i++) {
            cnt1[cs1[i] - 'a']++;
            cnt2[cs2[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
}