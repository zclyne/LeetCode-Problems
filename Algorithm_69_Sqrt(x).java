// 思路：用二分查找的方法，注意mid * mid可能越界，所以要通过mid与x / mid的关系来判断下一步的操作

class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1, right = x;
        while (true) {
            int mid = left + (right - left) / 2; // don't use (left + right) / 2, maybe overflow
            if (mid <= x / mid && mid + 1 > x / (mid + 1)) {
                return mid;
            } else if (x / mid < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }
}