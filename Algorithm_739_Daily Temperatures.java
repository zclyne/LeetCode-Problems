import java.util.Deque;
import java.util.LinkedList;

// 方法：单调栈
// 遍历temperatures，并维护一个单调递减的单调栈，栈中存储的是温度的下标
// 若当前温度高于栈顶元素的温度，说明当前温度是栈顶元素右边的第一个比它高的温度
// 从栈中pop出下标，当前下标和pop出来的下标之差就是要等待的天数，存入数组result

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int curTemp = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int top = stack.pop();
                result[top] = i - top;
            }
            stack.push(i);
        }
        return result;
    }
}