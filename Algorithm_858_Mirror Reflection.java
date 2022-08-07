// https://leetcode.com/problems/mirror-reflection/discuss/146336/Java-solution-with-an-easy-to-understand-explanation
// 相当于找使得m * p == n * q成立的m、n，并且m等于room extension次数+1，n等于光反射次数+1
// 本质上是最小公倍数问题，所以初始情况下，可以令m = q, n = p，此时上式一定成立
// 注意m和n不可能同时为偶数，不然就可以在等式m * p == n * q两边同时约去一个2，使得等式依然成立
// 所以退出循环时，m和n中至少有一个是奇数
// 有以下几种情况
// 1. 如果n是偶数，说明光最终停留在左边墙壁上，所以一定是2号接收器
// 2. 如果n是奇数，说明光最终停留在右边墙壁上
// 2.1 如果m是偶数，光最终到达0号接收器
// 2.2 如果m是奇数，光最终到达1号接收器
// 注意在上述讨论中，最后恰好到达接收器不算一次reflection

class Solution {
    public int mirrorReflection(int p, int q) {
        int m = q; // num of room extension + 1
        int n = p; // num of light reflection + 1
        while (m % 2 == 0 && n % 2 == 0) {
            m /= 2;
            n /= 2;
        }
        if (m % 2 == 0 && n % 2 == 1) {
            return 0;
        } else if (m % 2 == 1 && n % 2 == 1) {
            return 1;
        } else {
            return 2;
        }
    }
}