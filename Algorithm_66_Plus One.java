// 对digits从后向前遍历，判断当前digits[i]是否为9。若不为9，则+1后不会产生进位，直接digits[i]++，然后返回digits
// 若为9，则把当前位置为0，i--，继续循环
// 若for正常结束，说明始终在产生进位，此时要返回的结果数组res长度比digits多1，其res[0]为1而其他位全部为0

class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[] {1};
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}