import java.util.*;

// 方法：hashmap存储playerId到score的映射
// treemap存储score到当前score出现次数的映射
// addScore时，先根据playerId从playerIdToScore中取出这个player原本的score（或不存在，即0）
// 然后将原本的score从scoreToCount中减1，若原本value就是1，则删除
// 然后将原本的score加上score，得到新的score
// 将新的score存入scoreToCount，并将playerId到新的score的映射存入playerIdToScore
// top方法中，直接遍历scoreToCount。由于treeMap中的元素指定了按照从大到小的顺序存储，所以遍历的时候
// 自然已经是从大到小的顺序。因此只需要把前k个score相加就是答案
// reset方法就是把playerId到score的映射从playerIdToScore中删除，并把score从scoreToCount中减1，或删除

class Leaderboard {

    private Map<Integer, Integer> playerIdToScore;
    private TreeMap<Integer, Integer> scoreToCount;

    public Leaderboard() {
        playerIdToScore = new HashMap<>();
        scoreToCount = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void addScore(int playerId, int score) {
        int originalScore = playerIdToScore.getOrDefault(playerId, 0);
        // decrement original score count by 1
        if (scoreToCount.containsKey(originalScore)) {
            int count = scoreToCount.get(originalScore);
            if (count == 1) {
                scoreToCount.remove(originalScore);
            } else {
                scoreToCount.put(originalScore, count - 1);
            }
        }
        // increment the new score count by 1
        int newScore = originalScore + score;
        scoreToCount.put(newScore, scoreToCount.getOrDefault(newScore, 0) + 1);
        // store the mapping from player id to score
        playerIdToScore.put(playerId, playerIdToScore.getOrDefault(playerId, 0) + score);
    }
    
    public int top(int K) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : scoreToCount.entrySet()) {
            int score = e.getKey(), count = e.getValue();
            while (count > 0 && K > 0) {
                sum += score;
                count--;
                K--;
            }
            if (K == 0) {
                break;
            }
        }
        return sum;
    }
    
    public void reset(int playerId) {
        int score = playerIdToScore.get(playerId);
        playerIdToScore.remove(playerId);
        int count = scoreToCount.get(score);
        if (count == 1) {
            scoreToCount.remove(score);
        } else {
            scoreToCount.put(score, count - 1);
        }
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */