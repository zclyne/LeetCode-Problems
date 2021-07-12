import java.util.LinkedList;
import java.util.Queue;

// 解法：入栈时，向queue1尾部添加元素
// top和pop方法时，从queue1头部不断pop出元素，添加到queue2尾部，直到queue1中只剩下一个元素
// 这个元素就是栈顶元素
// 如果是top方法，则直接返回这个元素，而不将其删除
// 如果是pop方法，从queue1头部删除这个元素，然后将queue1和queue2互换，确保queue1中仍保留所有剩余元素
// 然后将这个元素返回

class MyStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (queue1.size() > 1) {
            int tmp = queue1.poll();
            queue2.offer(tmp);
        }
        int returnValue = queue1.poll();
        // swap queue1 and queue2
        Queue<Integer> tmpQueue = queue1;
        queue1 = queue2;
        queue2 = tmpQueue;
        return returnValue;
    }
    
    /** Get the top element. */
    public int top() {
        while (queue1.size() > 1) {
            int tmp = queue1.poll();
            queue2.offer(tmp);
        }
        return queue1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */