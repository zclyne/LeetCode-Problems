import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 方法1：记忆化搜索+DFS
// 数组canJump存储能否从一个石头跳到另一个石头
// canJump[i][j] = true就表示，能够从下标为i的石头跳到下标为j的石头
// 在DFS过程中，记起跳的石头下标为lastStoneIndex，当前所在的石头下标为curStoneIndex
// 如果canJump[lastStoneIndex][curStoneIndex] == true，则后续的DFS已经被执行过，直接return
// 否则，说明可以从lastStoneIndex跳到curStoneIndex
// 更新canJump[lastStoneIndex][curStoneIndex] = true
// 然后从stones中获得这一跳的距离jumpDistance，分别以jumpDistance - 1，jumpDistance，jumpDistance + 1为新的跳跃距离
// 从map中查找是否存在下一个可以跳到的石头。如果有，就进入下一层DFS
// 最后遍历一遍canJump的第n - 1列。如果发现某一个元素为true，说明可以跳到最后一个石头，返回true；否则返回false

class Solution {

    private Map<Integer, Integer> posToStone = new HashMap<>();
    private boolean[][] canJump;
    
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }

        int n = stones.length;
        canJump = new boolean[n][n];

        // store the mapping from position to stone index
        for (int i = 0; i < n; i++) {
            posToStone.put(stones[i], i);
        }

        dfs(1, 0, stones);

        for (int i = 0; i < n; i++) {
            if (canJump[i][n - 1]) {
                return true;
            }
        }

        return false;
    }

    private void dfs(int curStoneIndex, int lastStoneIndex, int[] stones) {
        if (canJump[lastStoneIndex][curStoneIndex]) {
            return;
        }

        canJump[lastStoneIndex][curStoneIndex] = true;

        int curPosition = stones[curStoneIndex];
        int jumpDistance = curPosition - stones[lastStoneIndex];
        int nextPosition = curPosition + jumpDistance - 1;

        for (int i = 0; i < 3; i++) {
            if (posToStone.containsKey(nextPosition)) {
                int nextStoneIndex = posToStone.get(nextPosition);
                dfs(nextStoneIndex, curStoneIndex, stones);
            }
            nextPosition++;
        }
    }
}



// 方法2：DP
// 整体思路和DFS相同，canJump数组的含义同上
// 嵌套循环。如果能够从石头from跳到石头cur，则计算从from到cur的跳跃距离jumpDistance
// 按照下一次跳跃的距离分别为jumpDistance - 1，jumpDistance和jumpDistance + 1讨论
// 查找是否存在cur的下一跳next。若存在，则记canJump[cur][next] = true

class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }

        int n = stones.length;
        boolean[][] canJump = new boolean[n][n];
        
        Map<Integer, Integer> posToStone = new HashMap<>();
        for (int i = 0; i < n; i++) {
            posToStone.put(stones[i], i);
        }

        // initial state
        canJump[0][1] = true;

        for (int cur = 1; cur < n; cur++) {
            for (int from = 0; from < cur; from++) {
                if (canJump[from][cur]) { // can jump from from to cur
                    int jumpDistance = stones[cur] - stones[from];
                    int nextPosition = stones[cur] + jumpDistance - 1;

                    for (int i = 0; i < 3; i++) {
                        if (posToStone.containsKey(nextPosition)) {
                            int next = posToStone.get(nextPosition);
                            canJump[cur][next] = true;
                        }
                        nextPosition++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (canJump[i][n - 1]) {
                return true;
            }
        }

        return false;
    }
}