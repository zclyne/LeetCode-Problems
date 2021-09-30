class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }

        int ans = 0;
        long dvd = Math.abs((long) dividend), dvs = Math.abs((long) divisor);
        while (dvd >= dvs) {
            long curDvs = dvs, tempAns = 1;
            while (curDvs << 1 <= dvd) {
                curDvs <<= 1;
                tempAns <<= 1;
            }
            dvd -= curDvs;
            ans += tempAns;
        }
        return sign * ans;
    }
}