import java.util.Deque;
import java.util.LinkedList;

// 方法：单调栈
// 维护一个单调递减的单调栈，并用变量day来记录当前的日期。栈中存储的是一个int[]，其中第一个元素是stock的price，第二个元素是当天的日期
// 初始状态下，栈中有一个元素，其price是Integer.MAX_VALUE，日期为0，以便处理特殊情况
// 每次调用next时，新输入一个price，就将其不断与栈顶元素的price进行比较，pop出所有price小于等于当前price的栈中元素，直到栈顶元素price大于当前price为止
// 此时，当前日期day和栈顶元素日期之差就是要返回的结果

class StockSpanner {

    private int day = 1;
    private Deque<int[]> stack;

    public StockSpanner() {
        stack = new LinkedList<>();
        stack.push(new int[]{Integer.MAX_VALUE, 0});
    }
    
    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        int result = day - stack.peek()[1];
        stack.push(new int[]{price, day});
        day++;
        return result;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */