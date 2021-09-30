class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int digit1 = i < 0 ? 0 : (num1.charAt(i) - '0');
            int digit2 = j < 0 ? 0 : (num2.charAt(j) - '0');
            int sum = digit1 + digit2 + carry;
            int newCarry = sum / 10;
            sum %= 10;
            stringBuilder.append(sum);
            carry = newCarry;
            i--;
            j--;
        }
        if (carry > 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }
}