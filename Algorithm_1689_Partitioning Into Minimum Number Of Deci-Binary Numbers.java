// 观察得到，10个1可以得到一个10，而10本身又是一个deci-binary
// 所以任何10个1相加所产生的一个进位都可以由进位后的数本身代替
// 因此任何进位都是不必要的，不需要考虑
// 所以所需的deci-binary的个数就等于字符串n中最大的digit

class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        for (char c : n.toCharArray()) {
            if (c - '0' > maxDigit) {
                maxDigit = c - '0';
            }
        }
        return maxDigit == 0 ? 1 : maxDigit;
    }
}