// write a function multiplyStringWithChar to implement multiplying a string with a single char
// write another function addStrings to implement adding two strings

class Solution {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String lastStr = "", result = "";
        int trailingZero = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            String curStr = multiplyStringWithChar(num2, num1.charAt(i), trailingZero);
            result = addStrings(lastStr, curStr);
            lastStr = result;
            trailingZero++;
        }
        return result;
    }

    private String multiplyStringWithChar(String num, char c, int trailingZero) {
        int digitOfC = c - '0';
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int curDigit = num.charAt(i) - '0';
            int product = digitOfC * curDigit + carry;
            int newDigit = product % 10;
            int newCarry = product / 10;
            stringBuilder.append(newDigit);
            carry = newCarry;
        }
        if (carry > 0) {
            stringBuilder.append(carry);
        }
        stringBuilder.reverse();
        for (int i = 0; i < trailingZero; i++) {
            stringBuilder.append(0);
        }
        return stringBuilder.toString();
    }

    private String addStrings(String num1, String num2) {
        if ("".equals(num1)) {
            return num2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int num1Digit = i >= 0 ? num1.charAt(i) - '0' : 0;
            int num2Digit = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = num1Digit + num2Digit + carry;
            int newDigit = sum % 10;
            int newCarry = sum / 10;
            stringBuilder.append(newDigit);
            carry = newCarry;
            i--;
            j--;
        }
        if (carry != 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }
}

// better solution

class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        // store the product of every pairing digit in num1 and num2 into products arr
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }

        // handle elements that are greater than or equal to 10
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}