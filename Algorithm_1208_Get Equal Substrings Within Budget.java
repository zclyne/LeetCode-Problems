// 思路：滑动窗口
// left和right分别记录当前所选中的子字符串区间，左闭右开
// 初始情况下，left == right == 0
// 每次尝试将right向右移动一格，并判断把新的字符加入进来后，是否已经超过最大所允许的cost
// 如果没有超过，则right移动成功，更新result
// 如果超过了，则从左侧缩小滑动窗口，直到能够将right所在的字符放入所选择的子字符串
// 并且不超过预算为止

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int left = 0, right = 0, result = 0, remainCost = maxCost;
        while (right < n) {
            int dist = Math.abs(s.charAt(right) - t.charAt(right));
            if (dist <= remainCost) { // can increment the window's right bound
                remainCost -= dist;
                result = Math.max(result, right - left + 1);
                right++;
            } else { // increment the window's left bound
                while (remainCost < dist) {
                    int tmp = Math.abs(s.charAt(left) - t.charAt(left));
                    remainCost += tmp;
                    left++;
                }
            }
        }
        return result;
    }
}