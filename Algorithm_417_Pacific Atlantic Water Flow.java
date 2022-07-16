// 从两个ocean的两条边上每个点分别开始dfs，每次都寻找高度大于等于当前高度的点，并把满足条件的点的坐标
// 分别存进两个set pacific和atlantic。最后取两个set的交集，再转换回List<Integer>放到结果中

import java.util.*;

class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        Set<String> pacific = new HashSet<>();
        Set<String> atlantic = new HashSet<>();
        // pacific
        // top row
        for (int j = 0; j < n; j++) {
            dfs(heights, 0, j, 0, pacific);
        }
        // left column
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, 0, pacific);
        }
        // atlantic
        // bottom row
        for (int j = 0; j < n; j++) {
            dfs(heights, m - 1, j, 0, atlantic);
        }
        // right column
        for (int i = 0; i < m; i++) {
            dfs(heights, i, n - 1, 0, atlantic);
        }
        // get the intersection
        pacific.retainAll(atlantic);
        List<List<Integer>> result = new ArrayList<>();
        for (String coordStr : pacific) {
            String[] coordArr = coordStr.split(",");
            List<Integer> temp = new ArrayList<>();
            temp.add(Integer.valueOf(coordArr[0]));
            temp.add(Integer.valueOf(coordArr[1]));
            result.add(temp);
        }
        return result;
    }

    private void dfs(int[][] heights, int i, int j, int lastHeight, Set<String> set) {
        int m = heights.length, n = heights[0].length;
        String key = String.valueOf(i) + "," + String.valueOf(j);
        if (i < 0 || i == m || j < 0 || j == n || heights[i][j] < lastHeight || set.contains(key)) {
            // heights[i][j] < lastHeight means rain cannot flow from the current grid to the last grid
            // set.contains(key) means the current grid has been visited
            return;
        }
        lastHeight = heights[i][j];
        set.add(key);
        this.dfs(heights, i - 1, j, lastHeight, set);
        this.dfs(heights, i + 1, j, lastHeight, set);
        this.dfs(heights, i, j - 1, lastHeight, set);
        this.dfs(heights, i, j + 1, lastHeight, set);
    }
}