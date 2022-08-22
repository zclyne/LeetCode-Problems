// 从len/2到1遍历可能的substring的length
// 对于某个特定length，若能被s.length()整除
// 则将其重复s.length() / len次，然后判断得到的字符串
// 和原始字符串s是否相等。若相等则返回true

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = s.length() / 2; i >= 1; i--) {
            if (s.length() % i != 0) {
                continue;
            }
            int m = s.length() / i;
            String substr = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(substr);
            }
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}