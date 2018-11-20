// 思路：map存储各个游戏状态下，当前玩家的输赢
// 游戏状态可以通过哪些数被使用来唯一确定
// 数组choosed保存被使用的数。由于boolean[]无法直接作为map的键，因此使用Arrays.toString()方法将数组转化为字符串后再作为map的key
// 注意此处不能使用choosed.toString()，数组名.toString()方法的结果是哈希
// dfs返回当前状态下，本轮玩家能否获胜。

class Solution {
    HashMap<String, Boolean> map;
    boolean[] choosed;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;
        map = new HashMap<>();
        choosed = new boolean[maxChoosableInteger + 1];
        return dfs(desiredTotal);
    }
    public boolean dfs(int desiredTotal) {
        if (desiredTotal <= 0) return false; // the player of the last turn wins, so the player of the current turn loses
        String choosedString = Arrays.toString(choosed); // don't use choosed.toString(), they are different
        if (!map.containsKey(choosedString)) {
            for (int i = 1; i < choosed.length; i++) {
                if (!choosed[i]) {
                    choosed[i] = true;
                    boolean tmpRes =  dfs(desiredTotal - i);
                    choosed[i] = false;
                    if (!tmpRes) {
                        map.put(choosedString, true);
                        return true;
                    }
                }
            }
            map.put(choosedString, false);
        }
        return map.get(choosedString);
    }
}