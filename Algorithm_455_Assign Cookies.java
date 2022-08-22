import java.util.Arrays;

// 对g和s排序，然后尽量把大的cookie assign给需要大cookie的child

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = g.length - 1, j = s.length - 1;
        int result = 0;
        while (i >= 0 && j >= 0) {
            if (g[i] <= s[j]) { // can assign cookie j to child i
                j--;
                result++;
            }
            i--;
        }
        return result;
    }
}