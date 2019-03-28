// 思路：用变量i、j记录当前在字符串a、b中的位置
// 把对应a[i]、b[j]以及进位carry相加得到cur，cur & 1(即cur % 2)是要加入的位
// cur >> 1(即cur / 2)是新的进位carry
// 当两字符串都读完后，判断carry是否为1，若为1则再添加一个1到builder的末尾
// 最后把builder反向，就是结果

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int cur = carry;
            cur += i >= 0 ? a.charAt(i) - '0' : 0;
            cur += j >= 0 ? b.charAt(j) - '0' : 0;
            carry = cur >> 1;
            cur &= 1;
            builder.append((char) ('0' + cur));
            i--;
            j--;
        }
        if (carry == 1) {
            builder.append('1');
        }
        return builder.reverse().toString();
    }
}