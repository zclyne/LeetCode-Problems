import java.util.*;



class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt[] = new int[3];
        for (int a: stones)
            cnt[a % 3]++;
        if (Math.min(cnt[1], cnt[2]) == 0)
            return Math.max(cnt[1], cnt[2]) > 2 && cnt[0] % 2 > 0;
        return Math.abs(cnt[1] - cnt[2]) > 2 || cnt[0] % 2 == 0;
    }
}

// 方法2：记忆化搜索，超时

class Solution {

    private Map<String, Boolean> cache;

    public boolean stoneGameIX(int[] stones) {
        cache = new HashMap<>();
        int[] stoneCount = new int[3];
        for (int stone : stones) {
            stoneCount[stone % 3]++;
        }
        return backtracking(0, stoneCount, true);
    }

    // returns whether the current player wins
    private boolean backtracking(int sum, int[] stoneCount, boolean aliceTurn) {
        if (stoneCount[0] == 0 && stoneCount[1] == 0 && stoneCount[2] == 0) {
            return !aliceTurn;
        }
        String stoneCountStr = getStoneCountStr(stoneCount);
        if (cache.containsKey(stoneCountStr)) {
            return cache.get(stoneCountStr);
        }

        for (int i = 0; i < 3; i++) {
            if ((sum + i) % 3 == 0 || stoneCount[i] == 0) { // unavailable
                continue;
            }
            stoneCount[i]--;
            boolean result = !backtracking(sum + i, stoneCount, !aliceTurn); // take the reverse because players take turns to play
            stoneCount[i]++;
            if (result) {
                cache.put(stoneCountStr, true);
                return true;
            }
        }
        cache.put(stoneCountStr, false);
        return false;
    }

    private String getStoneCountStr(int[] stoneCount) {
        return stoneCount[0] + ", " + stoneCount[1] + ", " + stoneCount[2];
    }
}