// 方法1：二分查找
// 注意必须用long，否则可能会溢出

class Solution {
    public boolean isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid >= num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left * left == num;
    }
}

// 方法2：牛顿迭代法

class Solution {
    public boolean isPerfectSquare(int num) {
      if (num < 2) return true;
  
      long x = num / 2;
      while (x * x > num) {
        x = (x + num / x) / 2;
      }
      return (x * x == num);
    }
}