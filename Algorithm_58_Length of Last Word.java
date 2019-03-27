// 思路：直接从后向前遍历，s.trim()的作用是去掉字符串s前后的连续空格

class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            res++;
        }
        return res;
    }
}