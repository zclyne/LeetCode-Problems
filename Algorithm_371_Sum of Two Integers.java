// 方法：在二进制下使用位运算进行计算

class Solution {
    public int getSum(int a, int b) {
        int result = 0, carry = 0;
        for (int i = 0; i < 32; i++) {
            int digit1 = (a >> i) & 1, digit2 = (b >> i) & 1;
            int newDigit = getNewDigit(digit1, digit2, carry);
            int newCarry = getNewCarry(digit1, digit2, carry);
            result = result | (newDigit << i);
            carry = newCarry;
        }
        return result;
    }

    private int getNewDigit(int digit1, int digit2, int carry) {
        if (digit1 == 0) {
            if (digit2 == 0) {
                return carry;
            } else { // digit2 == 1
                return carry == 0 ? 1 : 0;
            }
        } else { // digit1 == 1
            if (digit2 == 0) {
                return carry == 0 ? 1 : 0;
            } else { // digit2 == 1
                return carry;
            }
        }
    }

    private int getNewCarry(int digit1, int digit2, int carry) {
        if (digit1 == 0) {
            if (digit2 == 0) {
                return 0;
            } else { // digit2 == 1
                return carry == 0 ? 0 : 1;
            }
        } else { // digit1 == 1
            if (digit2 == 0) {
                return carry == 0 ? 0 : 1;
            } else { // digit2 == 1
                return 1;
            }
        }
    }
}