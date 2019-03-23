// 例子：https://leetcode.com/problems/count-and-say/discuss/15995/Examples-of-nth-sequence
// 思路：用StringBuilder存储上一行的结果，在计算当前行时，用变量count记录当前字符出现次数
// 在当前字符与上一个字符不一致时，把上一个字符的次数和值添加到记录当前行结果的StringBuilder（即temp）中
// 上一行遍历完后，res = temp

class Solution {
    public String countAndSay(int n) {
        int curLine = 1;
        StringBuilder res = new StringBuilder("1");
        while (curLine < n) {
            StringBuilder temp = new StringBuilder();
            int curCount = 0;
            for (int i = 0; i < res.length(); i++) {
                if (i == 0 || res.charAt(i) == res.charAt(i - 1)) {
                    curCount++;
                } else { // change to a different char
                    temp.append(String.valueOf(curCount));
                    temp.append((char) (res.charAt(i - 1)));
                    curCount = 1;
                }
            }
            if (curCount > 0) {
                temp.append(String.valueOf(curCount));
                temp.append((char) (res.charAt(res.length() - 1)));
            }
            res = temp;
            curLine++;
        }
        return res.toString();
    }
}