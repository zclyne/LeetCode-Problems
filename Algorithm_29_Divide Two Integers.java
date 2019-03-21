// The key observation is that the quotient of a division is just the number of times that we can subtract the divisor from the dividend without making it negative.
// Suppose dividend = 15 and divisor = 3, 15 - 3 > 0. We now try to subtract more by shifting 3 to the left by 1 bit (6). 
// Since 15 - 6 > 0, shift 6 again to 12. Now 15 - 12 > 0, shift 12 again to 24, which is larger than 15. 
// So we can at most subtract 12 from 15. Since 12 is obtained by shifting 3 to left twice, it is 1 << 2 = 4 times of 3. 
// We add 4 to an answer variable (initialized to be 0). The above process is like 15 = 3 * 4 + 3. We now get part of the quotient (4), with a remaining dividend 3.
// Then we repeat the above process by subtracting divisor = 3 from the remaining dividend = 3 and obtain 0. We are done.
// In this case, no shift happens. We simply add 1 << 0 = 1 to the answer variable.
// This is the full algorithm to perform division using bit manipulations. The sign also needs to be taken into consideration.
// And we still need to handle one overflow case: dividend = INT_MIN and divisor = -1.

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE && divisor == -1) { // will overflow
            return Integer.MAX_VALUE;
        } else if (Math.abs(divisor) == 1) {
            return divisor == 1 ? dividend : -dividend;
        }
        long dvd = Math.abs((long) dividend), dvs = Math.abs((long) divisor); // must use long here
        int res = 0;
        while (dvs <= dvd) {
            long curDvs = dvs; // must use long here
            int curRes = 1;
            while (curDvs << 1 <= dvd) {
                curDvs = curDvs << 1;
                curRes = curRes << 1;
            }
            dvd -= curDvs;
            res += curRes;
        }
        return (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) ? res : -res;
    }
}