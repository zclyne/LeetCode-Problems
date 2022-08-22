// 本质是等差数列求和
// 用二分查找+等差数列求和公式，找到最大的k
// 使得k * (k + 1) / 2 <= n
// 注意要用long，否则会溢出

class Solution {
    public int arrangeCoins(int n) {
        long left = 1, right = n;
        long temp;
        while (left < right) {
            long mid = left + (right - left) / 2;
            temp = mid * (mid + 1) / 2;
            if (temp <= n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        temp = left * (left + 1) / 2;
        if (temp <= n) {
            return (int) left;
        }
        return (int) left - 1;
    }
}