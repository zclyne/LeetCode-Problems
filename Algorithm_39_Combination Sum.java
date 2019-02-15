// 思路：先对candidates排序，保证其从小到大排列，然后使用DFS
// 用curIdx记录当前DFS的起始下标，cur记录当前已选择的所有数之和
// 从curIdx开始，若candidates中当前元素num + cur == target，则已经找到了一组满足要求的combination
// 将这组combination存放到res中并return，无需继续循环下去，因为之后的元素一定比当前num更大，不可能满足和为target
// 若当前num + cur < target，则把num加入curNums中，cur = cur + num，继续DFS

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    ArrayList<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        DFS(0, 0, target, candidates, new ArrayList<>());
        return res;
    }

    public void DFS(int curIdx, int cur, int target, int[] candidates, List<Integer> curNums) {
        for (int i = curIdx; i < candidates.length; i++) {
            int num = candidates[i];
            if (cur + num < target) { // valid
                curNums.add(num);
                DFS(i, cur + num, target, candidates, curNums);
                curNums.remove(curNums.size() - 1);
            } else if (cur + num == target) { // finish
                curNums.add(num);
                res.add(new ArrayList<Integer>(curNums));
                curNums.remove(curNums.size() - 1);
                return;
            }
        }
    }
}