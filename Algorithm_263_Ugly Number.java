// 方法：首先判断n是否是整数，若不是，直接返回false
// 然后用递归求解。base case是n == 1，返回true

class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        if (n % 5 == 0 && isUgly(n / 5)) {
            return true;
        } else if (n % 3 == 0 && isUgly(n / 3)) {
            return true;
        } else if (n % 2 == 0 && isUgly(n / 2)) {
            return true;
        }
        return false;
    }
}

// 迭代方法

class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }
}