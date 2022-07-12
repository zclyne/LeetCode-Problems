// 先遍历一遍s，得到s中所有'a'的数量
// 然后再遍历第二遍，注意循环在i == s.length() + 1时停止
// 第二遍循环尝试将下标i左边全部变成a，而右边全部变成b
// 所以要求把i左边的所有b删除，把右边的所有a删除
// 因此在遍历过程中，如果遇到a，就把countA--，表示右边的a的数量减少1
// 如果遇到b，就把countB++，表示左边的b的数量增加1
// 要删除的字符数就等于countA + countB
// 其最小值就是result

class Solution {
    public int minimumDeletions(String s) {
        int countA = 0, countB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                countA++;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= s.length(); i++) {
            if (i > 0) {
                char c = s.charAt(i - 1);
                if (c == 'a') {
                    countA--;
                } else {
                    countB++;
                }
            }
            result = Math.min(result, countA + countB);
        }
        return result;
    }
}