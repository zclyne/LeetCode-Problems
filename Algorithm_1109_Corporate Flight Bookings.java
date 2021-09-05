// 方法：差分数组
// 差分数组的第i个数即为原数组的第i-1个元素和第i个元素的差值，也就是说我们对差分数组求前缀和即可得到原数组
// 当我们希望对原数组的某一个区间[l, r]施加一个增量inc时，差分数组d对应的改变量是：d[l]增加inc，d[r + 1]减少inc
// 这样对于区间的修改就变为了对两个位置的修改，而这个修改的时间复杂度是O(1)
// 由于本题中从1开始，所以需要调整下标对应关系。对于booking = [l, r, inc]，需要让d[l - 1]增加inc，d[r]减少inc
// 而若r == n，无需修改d[r]，因为下标已经溢出
// https://leetcode-cn.com/problems/corporate-flight-bookings/solution/hang-ban-yu-ding-tong-ji-by-leetcode-sol-5pv8/

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diffArr = new int[n];
        for (int[] booking : bookings) {
            int l = booking[0], r = booking[1], seats = booking[2];
            diffArr[l - 1] += seats;
            if (r < n) { // if r == n, do nothing
                diffArr[r] -= seats;
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (i == 0 ? 0 : result[i - 1]) + diffArr[i];
        }
        return result;
    }
}