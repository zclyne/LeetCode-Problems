import java.util.*;

// 方法：把num转换成字符串，然后从右向左遍历，找每个digit右侧比它更大的最大的digit
// 如果这样的digit存在，就可以把这个最大的digit和当前digit交换，从而让num更大
// 要交换的下标存储在数组lastMaxDigitIndexes中
// lastMaxDigitIndexes数组构建完成后，再从左向右遍历lastMaxDigitIndexes，找到第一个值不为0的位置
// 就是要交换的位置
// 将两下标位置上的交换，再返回，即为结果

class Solution {
    public int maximumSwap(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();

        int lastMaxDigit = -1, lastMaxDigitIndex = -1;
        int[] lastMaxDigitIndexes = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int curDigit = numStr.charAt(i) - '0';
            
            if (curDigit > lastMaxDigit) {
                lastMaxDigit = curDigit;
                lastMaxDigitIndex = i;
            } else if (curDigit < lastMaxDigit) { // can swap durDigit and lastMaxDigit to form a bigger num
                lastMaxDigitIndexes[i] = lastMaxDigitIndex;
            }
        }

        int p = -1, q = -1;
        // look for the highest digit that can be swapped
        for (int i = 0; i < n; i++) {
            if (lastMaxDigitIndexes[i] != 0) { // can be swapped
                p = i;
                q = lastMaxDigitIndexes[i];
                break;
            }
        }

        if (p == -1 && q == -1) { // don't need to swap
            return num;
        }

        String newNumStr = numStr.substring(0, p) + numStr.charAt(q) + numStr.substring(p + 1, q) + numStr.charAt(p) + numStr.substring(q + 1);
        return Integer.parseInt(newNumStr);
    }
}