import java.util.ArrayList;
import java.util.List;

// simple backtracking

class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        backtracking(candidates, target, 0, new ArrayList<>());
        return result;
    }

    private void backtracking(int[] candidates, int target, int curPos, List<Integer> cur) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = curPos; i < candidates.length; i++) {
            int num = candidates[i];
            cur.add(num);
            backtracking(candidates, target - num, i, cur);
            cur.remove(cur.size() - 1);
        }
    }
}