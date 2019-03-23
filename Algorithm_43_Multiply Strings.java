// 思路：https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation

class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        int[] res = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int digit1 = num1.charAt(i) - '0', digit2 = num2.charAt(j) - '0';
                int curRes = digit1 * digit2 + res[i + j];
                res[i + j] = curRes % 10;
                res[i + j + 1] += curRes / 10;
            }
        }
        StringBuilder resBuilder = new StringBuilder();
        for (int i = num1.length() + num2.length() - 1; i >= 0; i--) {
            if (!(resBuilder.length() == 0 && res[i] == 0)) {
                resBuilder.append((char) (res[i] + '0'));
            }
        }
        return resBuilder.toString();
    }
}