import java.util.*;

// 用栈存放所有向右移动的asteroid
// 每次遇到一个新的向右移动的asteroid，就将其压入栈
// 如果遇到一个向左移动的asteroid，从栈中不断pop出size小于当前左移的asteroid的所有asteroid
// 这些asteroid都在向右移动，并且会被当前这个向左移动的asteroid所destroy
// 直到栈为空或者栈顶asteroid的size大于等于当前左移的asteroid
// 如果栈不为空，还有两种情况：
// 1. 栈顶asteroid的size等于当前asteroid的size。此时两个asteroid都会被destroy，都将destroyed中对应位置mark为true
// 2. 栈顶asteroid的size大于当前asteroid的size，此时栈顶asteroid与当前左移的asteroid碰撞后，会将左移的asteroid destroy，但自身依然存在
// 最后根据destroyed数组中的情况重新构建最终的结果

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        int resultLen = n;
        boolean[] destroyed = new boolean[n];
        // maintain a monotonically increasing stack
        Deque<Integer> stack = new LinkedList<>();

        // for each asteroid moving towards right, check all the asteroid
        // moving toward left that it can destroy, and whether itself would be destroyed
        for (int i = 0; i < n; i++) {
            if (asteroids[i] > 0) { // moving towards right
                stack.push(i);
            } else { // moving towards left
                int curSize = -asteroids[i];
                // find all the right-moving asteroids that will be destroyed by this left-moving asteroid
                while (!stack.isEmpty() && asteroids[stack.peek()] < curSize) {
                    destroyed[stack.pop()] = true;
                    resultLen--;
                }
                // the right-moving asteroid at the top of the stack has size >= curSize
                // so this left-moving asteroid will be destroyed as well
                if (!stack.isEmpty()) {
                    if (asteroids[stack.peek()] == curSize) {
                        destroyed[stack.pop()] = true;
                        resultLen--;
                    }
                    destroyed[i] = true;
                    resultLen--;
                }
            }
        }
        int[] result = new int[resultLen];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!destroyed[i]) {
                result[j++] = asteroids[i];
            }
        }
        return result;
    }
}