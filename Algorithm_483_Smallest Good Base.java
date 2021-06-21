// 解法1：二分法
// 由于n的最大值为10^18，取base最小的二进制时，能够在64位以内进行表示，所以1的个数的最大值不超过64
// 64这个值很小，所以可以对1的个数进行枚举，在枚举过程中，对base进行二分
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        long result = num - 1; // maximum of result is num - 1;
        // 对1的个数进行枚举
        for (int i = 3; i < 64; i++) {
            long left = 2, right = num;
            while (left < right) {
                long mid = left + (right - left) / 2;
                long curRes = this.calculate(mid, i, num);
                if (curRes < 0) { // overflow, base is too large
                    right = mid;
                } else if (curRes == num) { // mid is a valid base
                    result = Long.min(result, mid);
                    break;
                } else if (curRes > num) { // base is too large
                    right = mid;
                } else { // base is too small
                    left = mid + 1;
                }
            }
        }
        return String.valueOf(result);
    }

    private long calculate(long k, long numOfOne, long num) {
        long result = 0;
        for (int i = 0; i < numOfOne; i++) {
            if (num / k < result - 1) { // overflow
                return -1;
            }
            result = result * k + 1;
        }
        return result;
    }
}

// 解法2：数学方法
// https://leetcode-cn.com/problems/smallest-good-base/solution/zui-xiao-hao-jin-zhi-by-leetcode-solutio-csqn/
class Solution {
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }
}