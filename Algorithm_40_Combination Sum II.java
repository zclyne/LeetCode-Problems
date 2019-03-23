import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

// My Solution
// 先对candidates排序，方便在递归过程中根据candidates[cur]的值提前return，且方便判断重复
// 使用HashSet来存放每一个符合条件的List，好处是可以自动去除重复
// 递归过程中，使用cur来记录当前位置，若candidates[cur] > target，由于candidates已经排序，之后的元素一定都不合适，因此可以直接返回
// 若candidates[cur] <= target，有2种可选的分支：是否把candidates[cur]加入curList中
// 若不加入，则直接递归调用helper(curList, candidates, target, cur + 1)
// 若加入，则加入后调用helper(curList, candidates, target - candidates[cur], cur + 1)
// 注意这个递归完成后，还要把candidates[cur]从curList的尾部删除，防止影响之后的递归
// 递归全部完成后，把set转换成List并返回

class Solution {

    private HashSet<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(new ArrayList<Integer>(), candidates, target, 0);
        return new ArrayList<List<Integer>> (res);
    }

    private void helper(List<Integer> curList, int[] candidates, int target, int cur) {
        if (target == 0 && curList.size() > 0) {
            res.add(new ArrayList<Integer>(curList));
        }
        if (cur == candidates.length || candidates[cur] > target) {
            return;
        }
        helper(curList, candidates, target, cur + 1);
        curList.add(candidates[cur]);
        helper(curList, candidates, target - candidates[cur], cur + 1);
        curList.remove(curList.size() - 1); // this step is necessary
    }

}



// Optimized Solution
// 思路：与My Solution一样DFS，通过循环并判断candidates[i] == candidates[i - 1]来避免重复

class OptimizedSolution {

    private ArrayList<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(new ArrayList<Integer>(), candidates, target, 0);
        return res;
    }

    private void helper(List<Integer> curList, int[] candidates, int target, int cur) {
        if (target == 0 && curList.size() > 0) {
            res.add(new ArrayList<Integer>(curList));
        }
        if (cur == candidates.length || candidates[cur] > target) {
            return;
        }
        for (int i = cur; i < candidates.length; i++){
            if (i > cur && candidates[i] == candidates[i-1]) continue; // avoid duplicate
            curList.add(candidates[i]);
            helper(curList, candidates, target - candidates[i], i + 1);
            curList.remove(curList.size()-1); // necessary
        }
    }

}