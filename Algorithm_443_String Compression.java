// 双指针，cur记录遍历原始chars的指针的当前位置
// newLen记录用于覆盖chars中原始内容的指针的位置

class Solution {
    public int compress(char[] chars) {
        int cur = 0, newLen = 0;
        while (cur < chars.length) {
            char c = chars[cur];
            int count = 1;
            while (cur < chars.length - 1 && chars[cur + 1] == c) {
                cur++;
                count++;
            }
            cur++; // move to the new letter
            chars[newLen++] = c;
            if (count > 1) { // multiple chars
                // append the count
                String countStr = String.valueOf(count);
                for (int i = 0; i < countStr.length(); i++) {
                    chars[newLen++] = countStr.charAt(i);
                }
            }
        }
        return newLen;
    }
}