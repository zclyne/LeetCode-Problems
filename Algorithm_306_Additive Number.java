// 方法：回溯法
// 在backtracking方法上加一个参数numCount，记录当前已经使用的数字的个数
// 在num遍历完成后，需要检查numCount是否>=3，如果>=3，说明找到了一组合适的additive sequence，返回true
// 否则，返回false
// 变量pos记录当前回溯法的开始位置
// 如果num.charAt(pos) == '0'，由于数字不能包含前导0，所以必须向后移动一位，或返回false
// 否则，循环遍历num的剩余部分，并将num从pos到i的部分解析成数字curNum。如果n1 + n2 == curNum，或者numCount < 3
// 说明可以继续进入下一层递归
// 如果curNum > n1 + n2，则没有继续向后进行for循环的必要，因为curNum只会越来越大，可以直接break

class Solution {
    public boolean isAdditiveNumber(String num) {
        return backtracking(num, 0, 0, 0, 0);
    }

    private boolean backtracking(String num, int pos, long n1, long n2, int numCount) {
        if (pos == num.length()) {
            return numCount >= 3;
        }

        numCount++;
        // number cannot have leading 0, so when the digit is 0, advance pos by 1
        if (num.charAt(pos) == '0') {
            return (numCount < 3 || n1 + n2 == 0) && backtracking(num, pos + 1, n2, 0, numCount);
        }

        long curNum = 0;
        for (int i = pos; i < num.length(); i++) {
            curNum = curNum * 10 + (num.charAt(i) - '0');
            if (numCount < 3 || curNum == n1 + n2) {
                if (backtracking(num, i + 1, n2, curNum, numCount)) {
                    return true;
                }
            } else if (curNum > n1 + n2) {
                break;
            }
        }
        return false;
    }
}

// 另一种回溯法
// https://leetcode-cn.com/problems/additive-number/solution/xia-biao-zuo-wei-fen-duan-dian-dfs-by-over-lord/

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        if (n < 3) return false;
        for (int j = 1; j <= n / 2; j++) {
            for (int k = j + 1; k < n; k++) {
                if (backtrace(num, 0, j, k)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean backtrace(String s, int i, int j, int k) {
        if (s.charAt(i) == '0' && j - i > 1 || s.charAt(j) == '0' && k - j > 1) {
            return false;
        }
        String sum = add(s.substring(i, j), s.substring(j, k));
        if (sum.length() > s.length() - k || !sum.equals(s.substring(k, k + sum.length()))) {
            return false;
        }
        if (sum.length() == s.length() - k) {
            return true;
        }

        return backtrace(s, j, k, k + sum.length());
    }

    public String add(String s1, String s2) {
        int n1 = s1.length() - 1;
        int n2 = s2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry > 0) {
            int t1 = n1 >= 0 ? (int)(s1.charAt(n1) - '0') : 0;
            int t2 = n2 >= 0 ? (int)(s2.charAt(n2) - '0') : 0;
            int sum = t1 + t2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            n1--;
            n2--;
        }

        return sb.reverse().toString();
    }
}