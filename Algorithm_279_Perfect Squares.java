import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

// Solution 1: DP
// 思路：dp[i]存储把i分解为完全平方数之和时所需的完全平方数的最小个数
// 初始时，先把所有的完全平方数num对应的dp[num]置为1，然后判断dp[n]是否非0，若非0，说明n本就是完全平方数，返回1
// i从2开始循环增长到n，嵌套一个内层循环把i分解为一个完全平方数j * j和另一个数i - j * j，可以减少内层循环的次数
// 最后dp[n]就是答案

class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 0) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    min = Math.min(min, dp[j * j] + dp[i - j * j]);
                }
                dp[i] = min;
            }
        }
        return dp[n];
    }
}


// Solution 2: BFS
// 把数字1~n看作一张图的所有节点，当且仅当两数之差为一个完全平方数时，两节点间有一条边
// 初始时，先把所有完全平方数存到数组perfectSquares中，并创建数组visited用于记录某个数是否已经被访问过
// 若visited[n]，表明n本身是一个完全平方数，直接返回1
// 把perfectSquares中的所有数加入队列中，开始循环。每次循环时，取出队列中的所有元素，并将其与各个完全平方数相加得到一个新的数。
// 如果这个数小于n且还未被访问过，就将它加入队列
// 注意while内部的第一个for循环要先记录下队列当前的size然后再开始循环，因为循环体内有可能向队列中添加元素而导致size改变
// 变量count用于记录走过的边的数量，即需要的完全平方数的个数

class Solution2 {
    public int numSquares(int n) {
        ArrayList<Integer> perfectSquares = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i * i <= n; i++) {
            perfectSquares.add(i * i);
            visited[i * i] = true;
        }
        if (visited[n]) {
            return 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        for (Integer squareNum : perfectSquares) {
            queue.offer(squareNum);
        }
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curNum = queue.poll();
                for (Integer squareNum : perfectSquares) {
                    if (curNum + squareNum == n) {
                        return count;
                    } else if (curNum + squareNum < n && !visited[curNum + squareNum]) { // not visited
                        visited[curNum + squareNum] = true;
                        queue.offer(curNum + squareNum);
                    } else if (curNum + squareNum > n) { // don't need to handle numbers greater than n
                        break;
                    }
                }
            }
        }
        return 0;
    }
}