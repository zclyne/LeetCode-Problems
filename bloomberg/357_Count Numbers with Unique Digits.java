// for a number of n digits, the total number of numbers with unique digits are:
// 10Pn - 9P(n - 1)
// 10Pn means selecting n digits from 10 digits and get the number of all the permutation with these n digits
// 9P(n - 1) means when the first digit is 0, there are n - 1 digits left, and we can select n - 1 digits from 1 ~ 9

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 10;
        }
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 10;
        for (int i = 2; i <= n; i++) {
            int p = 1;
            for (int j = 10 - i + 1; j <= 10; j++) {
                p *= j;
            }
            int q = 1;
            for (int j = 10 - i + 1; j <= 9; j++) {
                q *= j;
            }
            count[i] = p - q;
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += count[i];
        }
        return result;
    }
}