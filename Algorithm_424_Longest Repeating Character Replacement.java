// 思路：滑动窗口
// https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
// 数组map存储当前滑动窗口内的字符串中各个字符的出现次数
// historyCharMax存储滑动窗口内相同字母出现次数的历史最大值
// 如果当前字符串中的出现次数最多的字母个数+K大于字符串长度，说明这个字符串可以通过
// 替换字符的方式来达到全部都是重复字符，所以窗口可以继续扩大
// 否则，就将left++，维持窗口的大小不变
// historyCharMax的值只增大，不减小的原因是，我们只在乎窗口能否继续扩大，而如果当前
// left++后，恰好把出现次数最多的字符移除出去，对于最终的结果是没有影响的

class Solution {
    private int[] map = new int[26];

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }
}