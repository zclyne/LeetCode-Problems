import java.util.Deque;
import java.util.LinkedList;

// traverse from right to left, and save the max height that have ever seen

class Solution {
    public int[] findBuildings(int[] heights) {
        int maxHeight = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeight) {
                stack.push(i);
                maxHeight = heights[i];
            }
        }
        int[] result = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }
}