class Solution {
    public int maxRotateFunction(int[] A) {
        int len = A.length;
        if (len == 0) return 0;
        int sum = 0, startSum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++)
        {
            sum += A[i];
            startSum += i * A[i];
        }
        for (int flag = len - 1; flag >= 0; flag--)
        {
            startSum = startSum + sum - len * A[flag];
            maxSum = Math.max(startSum, maxSum);
        }
        return maxSum;
    }
}

// Brute force. Time Limit Exceeded
class Solution {
    public int maxRotateFunction(int[] A) {
        int len = A.length;
        if (len == 0) return 0;
        int maxSum = Integer.MIN_VALUE;
        for (int n = 0; n < len; n++)
        {
            int start = (len - n) % len, tmpSum = 0;
            for (int i = 0; i < len; i++)
            {
                tmpSum += i * A[start];
                start = (start + 1) % len;
            }
            maxSum = Math.max(maxSum, tmpSum);
        }
        return maxSum;
    }
}