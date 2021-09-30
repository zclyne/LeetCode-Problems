class Solution {
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int digit1 = i < 0 ? 0 : (a.charAt(i) - '0');
            int digit2 = j < 0 ? 0 : (b.charAt(j) - '0');
            int sum = digit1 + digit2 + carry;
            int newCarry = sum / 2;
            sum %= 2;
            stringBuilder.append(sum);
            carry = newCarry;
            i--;
            j--;
        }
        if (carry == 1) {
            stringBuilder.append(1);
        }
        return stringBuilder.reverse().toString();
    }
}