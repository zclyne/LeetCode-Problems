// 思路：先对candidates排序，保证其从小到大排列，然后使用DFS
// 用curIdx记录当前DFS的起始下标，cur记录当前已选择的所有数之和
// 从curIdx开始，若candidates中当前元素num + cur == target，则已经找到了一组满足要求的combination
// 将这组combination存放到res中并return，无需继续循环下去，因为之后的元素一定比当前num更大，不可能满足和为target
// 若当前num + cur < target，则把num加入curNums中，cur = cur + num，继续DFS

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        DFS(candidates, target, 0, new ArrayList<>());
        return result;
    }
    private void DFS(int[] candidates, int target, int index, List<Integer> cur) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        if (candidates[index] > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            cur.add(candidates[i]);
            DFS(candidates, target - candidates[i], i, cur);
            cur.remove(cur.size() - 1);
        }
    }
}