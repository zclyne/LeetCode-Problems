// 思路：数组maxLens保存的是字符串p中以各个字母起始的最长满足条件的substring的长度，该最大长度即为以该字母开始的substring的数量
// 遍历字符串p结束后，将maxLens中的所有值求和即为答案

class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() <= 1) return p.length();
        int[] maxLens = new int[26];
        int curLen = 1, res = 0, startCharOffset = p.charAt(0) - 'a';
        maxLens[startCharOffset] = 1;
        for (int i = 1; i < p.length(); i++) {
            char lastChar = p.charAt(i - 1), curChar = p.charAt(i);
            int lastOffset = lastChar - 'a', curOffset = curChar - 'a';
            if (curOffset == (lastOffset + 1) % 26) curLen++;
            else {
                for (int j = startCharOffset; curLen > 0; j = (j + 1) % 26) {
                    maxLens[j] = Integer.max(maxLens[j], curLen);
                    curLen--;
                }
                startCharOffset = curOffset;
                curLen = 1;
            }
        }
        // handle the last substring
        for (int j = startCharOffset; curLen > 0; j = (j + 1) % 26) {
            maxLens[j] = Integer.max(maxLens[j], curLen);
            curLen--;
        }
        for (int i = 0; i < 26; i++) res += maxLens[i];
        return res;
    }
}