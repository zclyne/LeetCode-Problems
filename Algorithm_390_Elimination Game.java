class Solution {
    public int lastRemaining(int n) {
        int min = 1, max = n;
        int interval = 1;
        boolean LTOR = true;
        while (n > 2)
        {
            if (LTOR) // left to right
            {
                min += interval;
                if (n % 2 == 1) max -= interval;
            }
            else
            {
                max -= interval;
                if (n % 2 == 1) min += interval;
            }
            LTOR = !LTOR;
            n /= 2;
            interval *= 2;
        }
        if (n == 1) return min;
        return LTOR ? max : min;
    }
}