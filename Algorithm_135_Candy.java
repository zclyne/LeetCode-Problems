// 思路：

class Solution {
    public int candy(int[] ratings) {
        int continuousMonotonicCount = 0, result = 0;
        // 1 for increasing, -1 for decreasing, 0 for not change
        // for the initial situation, prevDirection is 0
        int prevDirection = 0;
        int lastContinuousIncreasingCount = 0;
        boolean flag = false;
        for (int i = 0; i < ratings.length; i++) {
            int nextDirection = getDirection(ratings, i);
            if (prevDirection == 1) {
                continuousMonotonicCount++;
                result += continuousMonotonicCount;
                if (nextDirection == 1) {
                    // do nothing
                } else if (nextDirection == 0) {
                    continuousMonotonicCount = 1;
                } else { // this is the tricky part
                    lastContinuousIncreasingCount = continuousMonotonicCount;
                    flag = true;
                    continuousMonotonicCount = 1;
                }
            } else if (prevDirection == 0) {
                continuousMonotonicCount = 1;
                result += continuousMonotonicCount;
            } else {
                continuousMonotonicCount++;
                result += continuousMonotonicCount;
                if (nextDirection == 1) {
                    if (flag) {
                        if (lastContinuousIncreasingCount > continuousMonotonicCount) {
                            result += lastContinuousIncreasingCount - continuousMonotonicCount;
                        }
                        flag = false;
                    }
                    continuousMonotonicCount = 1;
                } else if (nextDirection == 0) {
                    if (flag) {
                        if (lastContinuousIncreasingCount > continuousMonotonicCount) {
                            result += lastContinuousIncreasingCount - continuousMonotonicCount;
                        }
                        flag = false;
                    }
                    continuousMonotonicCount = 1;
                } else {
                    // do nothing
                }
            }
            prevDirection = nextDirection;
        }
        return result;
    }

    private int getDirection(int[] ratings, int i) {
        if (i == ratings.length - 1) { // last element
            return 0;
        } else {
            if (ratings[i + 1] > ratings[i]) {
                return 1;
            } else if (ratings[i + 1] == ratings[i]) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}