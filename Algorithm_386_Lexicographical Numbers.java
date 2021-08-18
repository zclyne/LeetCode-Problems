import java.util.ArrayList;
import java.util.List;

// 思路：回溯法
// 如果当前数字cur已经大于n，则可以直接返回，不作任何处理
// 否则，将cur加入到结果列表result中
// 枚举cur的下一位。如果cur是0，则从1开始枚举，否则从0开始，枚举的起点记为start
// cur * 10 + i就是下一层回溯时的cur

class Solution {

    private List<Integer> result;

    public List<Integer> lexicalOrder(int n) {
        result = new ArrayList<>();
        helper(n, 0);
        return result;
    }

    private void helper(int n, int cur) {
        int start = 0;
        if (cur == 0) {
            start = 1;
        } else {
            if (cur > n) {
                return;
            } else {
                result.add(cur);
            }
        }

        for (int i = start; i <= 9; i++) {
            helper(n, cur * 10 + i);
        }
    }
}