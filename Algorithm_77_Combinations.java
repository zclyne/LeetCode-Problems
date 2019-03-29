import java.util.List;
import java.util.ArrayList;

// 思路：递归回溯。用k记录剩余需要加入的数字的个数。当k == 0时，不需要再加入新的数字，则把curList加入res中
// cur表示上一层递归中被加入curList的数字，为防止重复，当前递归从cur + 1开始循环
// 循环变量i的最大值是n - k + 1，这是为了保证剩余的数字个数 >= k个

class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        helper(0, n, k, new ArrayList<Integer>());
        return res;
    }

    private void helper(int cur, int n, int k, List<Integer> curList) {
        if (k == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = cur + 1; i <= n - k + 1; i++) {
            curList.add(i);
            helper(i, n, k - 1, curList);
            curList.remove(curList.size() - 1);
        }
    }

}