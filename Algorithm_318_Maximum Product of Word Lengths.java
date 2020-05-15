// 思路：values[i]存储words[i]中出现过的字母，一个int有32位，可以存下26位小写字母
// values[i]的第word.charAt(j) - 'a'位为1就表示word[j]出现过
// 若values[i]和values[j]的与为0，就表示words[i]和words[j]不存在共有的字符

class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }
        int[] values = new int[words.length];
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                values[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((values[i] & values[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}