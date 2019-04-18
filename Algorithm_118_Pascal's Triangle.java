import java.util.List;
import java.util.ArrayList;

// 思路：直接循环。i记录当前的层序号，j记录当前层中的位置

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        for (int i = 1; i <= numRows; i++) {
            List<Integer> curLayer = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                curLayer.add((j == 0 || j == i - 1) ? 1 : (res.get(i - 2).get(j - 1) + res.get(i - 2).get(j))); // notice i - 2 here
            }
            res.add(curLayer);
        }
        return res;
    }
}