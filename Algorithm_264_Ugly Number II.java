import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 方法1：最小堆。利用堆本身的有序的性质
// 注意要额外使用一个set来进行去重
// 还要注意溢出问题，必须使用long类型

class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        for (int i = 1; i < n; i++) {
            long top = pq.poll();
            if (!set.contains(top * 2)) {
                pq.add(top * 2);
                set.add(top * 2);
            } 
            if (!set.contains(top * 3)) {
                pq.add(top * 3);
                set.add(top * 3);
            }
            if (!set.contains(top * 5)) {
                pq.add(top * 5);
                set.add(top * 5);
            }
        }
        long result = pq.poll();
        return (int) result;
    }
}



// 方法2：动态规划
// 定义3个指针p2, p3, p5，分别表示下一个ugly number是当前指针指向的ugly number * 对应因数
// 正确性证明：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/

class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int n2 = 2 * dp[p2], n3 = 3 * dp[p3], n5 = 5 * dp[p5];
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                p2++;
            }
            // 注意不能写else if，因为有可能n2和n3相同，此时两个指针都要++
            if (dp[i] == n3) {
                p3++;
            }
            if (dp[i] == n5) {
                p5++;
            }
        }

        return dp[n];
    }
}