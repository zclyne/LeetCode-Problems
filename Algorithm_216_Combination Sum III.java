import java.util.ArrayList;
import java.util.List;

// 方法1：递归，数组selected记录已经选用的数字，记上一次选择的数字为lastSelected
// 由于每个数字最多只能用一次，因此对于下一个数字，只能从lastSelected + 1开始选择

class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        findCombination(k, new ArrayList<>(), n);
        return result;
    }

    private void findCombination(int k, List<Integer> selected, int n) {
        if (k == 0) {
            if (n == 0) { // found a valid answer
                result.add(new ArrayList<>(selected));
            }
            return;
        }
        if (n <= 0) {
            return;
        }
        int lastSelected = selected.isEmpty() ? 0 : selected.get(selected.size() - 1);
        for (int i = lastSelected + 1; i <= 9; i++) {
            selected.add(i);
            findCombination(k - 1, selected, n - i);
            selected.remove(selected.size() - 1);
        }
    }
}

// 方法2：二进制（子集）枚举
// 用9位二进制数mask来记录当前所有位置的状态。从低到高第i位为0表示i不被选择到子集中，为1表示i被选择到子集中
// 遍历0到2^9 - 1的所有数，就能够枚举出所有可能的子集组合
// https://leetcode-cn.com/problems/combination-sum-iii/solution/zu-he-zong-he-iii-by-leetcode-solution/

class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        for (int mask = 0; mask < (1 << 9); ++mask) {
            if (check(mask, k, n)) {
                ans.add(new ArrayList<Integer>(temp));
            }
        }
        return ans;
    }

    public boolean check(int mask, int k, int n) {
        temp.clear();
        for (int i = 0; i < 9; ++i) {
            if (((1 << i) & mask) != 0) {
                temp.add(i + 1);
            }
        }
        if (temp.size() != k) {
            return false;
        }
        int sum = 0;
        for (int num : temp) {
            sum += num;
        }
        return sum == n;
    }
}