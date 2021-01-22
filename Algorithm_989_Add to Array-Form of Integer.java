import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 思路：从后向前逐位相加

class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        int i = A.length - 1;
        int sum = 0, newDigit = 0, carry = 0;
        while (i >= 0 || K > 0) {
            int digitInK = K % 10;
            K = K / 10;
            int digitInA = 0;
            if (i >= 0) {
                digitInA = A[i];
                i--;
            }
            sum = digitInA + digitInK + carry;
            newDigit = sum % 10;
            carry = sum / 10;
            result.add(0, newDigit);
        }
        if (carry != 0) {
            result.add(0, 1);
        }
        return result;
    }
}