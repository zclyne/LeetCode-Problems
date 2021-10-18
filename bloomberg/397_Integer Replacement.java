// if n % 2 == 0, then n = n / 2
// else if n == 3, then n -= 1
// else, check the last 2 digits of n
// if 11: n += 1
// if 01: n -= 1
// must use >>> instead of >>

class Solution {
    public int integerReplacement(int n) {
        int count = 0;
        
        while (n != 1) {
            if (n % 2 == 0) {
                n = n >>> 1;
            } else {
                if (n == 3) {
                    n = 2;
                } else {
                    if ((n & 3) == 3) {
                        n += 1;
                    } else {
                        n -= 1;
                    }
                }
            }
            count++;
        }

        return count;
    }
}