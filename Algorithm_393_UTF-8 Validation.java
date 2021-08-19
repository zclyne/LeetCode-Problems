// 方法：直接分5种情况讨论
// 遍历data，对于当前的数字num，根据其二进制分别以0开始、以110开始、以1110开始、以11110开始和其他，这5种情况讨论
// 1. 以0开始，则说明是1个byte的utf8字符，直接i++
// 2. 以110开始，说明是2个byte的utf8字符，i++后判断data[i]是否以10开始，若是，则继续i++；若不是，则返回false
// 3. 以1110开始，说明是3个byte的utf8字符，分别判断后面2个数字是否都以10开始。只要有一个不满足，则返回false
// 4. 以11110开始，说明是4个byte的utf8字符，分别判断后面3个数字是否都以10开始。只要有一个不满足，则返回false
// 5. 以上都不满足，直接返回false

class Solution {
    public boolean validUtf8(int[] data) {
        int i = 0;
        while (i < data.length) {
            int num = data[i];
            if (startsWith0(num)) {
                i++;
            } else if (startsWith110(num)) {
                i++;
                if (i < data.length && startsWith10(data[i])) {
                    i++;
                } else {
                    return false;
                }
            } else if (startsWith1110(num)) {
                i++;
                for (int j = 0; j < 2; j++) {
                    if (i < data.length && startsWith10(data[i])) {
                        i++;
                    } else {
                        return false;
                    }
                }
            } else if (startsWith11110(num)) {
                i++;
                for (int j = 0; j < 3; j++) {
                    if (i < data.length && startsWith10(data[i])) {
                        i++;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean startsWith0(int n) {
        n >>= 7;
        return n == 0;
    }

    private boolean startsWith110(int n) {
        n >>= 5;
        return n == 6;
    }

    private boolean startsWith1110(int n) {
        n >>= 4;
        return n == 14;
    }

    private boolean startsWith11110(int n) {
        n >>= 3;
        return n == 30;
    }

    private boolean startsWith10(int n) {
        n >>= 6;
        return n == 2;
    }
}