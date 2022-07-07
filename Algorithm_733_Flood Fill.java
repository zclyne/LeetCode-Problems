// 直接DFS。只有当当前pixel的颜色等于originalColor，并且
// 当前pixel的颜色不等于targetColor时，才能继续递归DFS
// 否则就直接返回。递归DFS之前，先把当前pixel的颜色修改成targetColor，可以防止重复访问

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    private void dfs(int[][] image, int x, int y, int originalColor, int targetColor) {
        int m = image.length, n = image[0].length;
        // index out of bound or the color has already been changed
        if (x < 0 || x == m || y < 0 || y == n ||
            image[x][y] != originalColor ||
            image[x][y] == targetColor) {
            return;
        }
        image[x][y] = targetColor;

        dfs(image, x - 1, y, originalColor, targetColor);
        dfs(image, x + 1, y, originalColor, targetColor);
        dfs(image, x, y - 1, originalColor, targetColor);
        dfs(image, x, y + 1, originalColor, targetColor);
    }
}