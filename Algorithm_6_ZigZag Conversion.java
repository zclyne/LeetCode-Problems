// 思路：首先判断numRows是否为1，若为1则不存在zigzag，直接返回s
// 构造一个StringBuidler的数组，用于存放每一行的字符，数组的第i个元素表示第i行
// 开始遍历s，用变量curRow记录当前字符应该放在哪一行，asc记录当前应该递增还是递减，把s[i]放入rows[curRow]中的StringBuilder的尾部
// 最后把所有StringBuilder中的内容添加到同一个StringBuilder res中，注意要先判断res[i]是否为空。因为可能存在s.length() < numRows的情况，此时rows中最后几个StringBuilder是null
// 最后返回res.toString()

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        int curRow = 0;
        boolean asc = true;
        for (int i = 0; i < s.length(); i++) {
            if (rows[curRow] == null) {
                rows[curRow] = new StringBuilder();
            }
            rows[curRow].append(s.charAt(i));
            if (curRow == numRows - 1) {
                asc = false;
            } else if (curRow == 0) {
                asc = true;
            }
            curRow = asc ? curRow + 1 : curRow - 1;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (rows[i] != null) {
                res.append(rows[i]);
            }
        }
        return res.toString();
    }
}