// Solution 1: One Stack
// 思路：在每次min发生改变时，就把改变前的min压入stack中，出栈时，判断出栈元素是否等于当前的min
// 若等于，说明在压入这个数时产生了min的改变，因此要连续pop两次，且第二次pop出来的值就是在压入这个数之前的min值，把这个数再次赋给min

import java.util.Stack;
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (min == stack.pop()) min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

// Solution 2: Two Stack
// 思路：用2个栈，一个作为实际用的栈，另一个保存最小值。当有数被push进来时，比较该数和minStack栈顶元素的大小
// 若新的数更小，则将其入栈。这样，minStack的栈顶元素始终是当前stack中的最小元素
// 可以分两种情况：
// 1. 先push一个较小的元素，后push一个较大的元素：较小的元素进入minStack，而较大的元素不进入minStack，此时minStack栈顶是较小的元素
// 此时，无论较大的元素是否被pop出去，只要较小的元素还在stack中，minStack的栈顶就是这个较小的元素，直至它被从stack中pop出去为止
// 2. 先push一个较大的元素，后push一个较小的元素：两元素按照进stack的顺序进入minStack，在较小的元素被pop之前，minStack栈顶是这个较小的元素
// 在它被pop之后，原本较大的元素成为了新的minStack栈顶元素，也就是新的较小的元素
// 两种情况下都保证了正确性

// class MinStack {

//     private Stack<Integer> stack = new Stack<>();
//     private Stack<Integer> minStack = new Stack<>();

//     /** initialize your data structure here. */
//     public MinStack() {
        
//     }
    
//     public void push(int x) {
//         stack.push(x);
//         if (minStack.isEmpty() || x <= minStack.peek()) {
//             minStack.push(x);
//         }
//     }
    
//     public void pop() {
//         if (stack.peek().equals(minStack.peek())) { // we must use .equals() here because .peek() return Integer, rather than int
//             minStack.pop();
//         }
//         stack.pop();
//     }
    
//     public int top() {
//         return stack.peek();
//     }
    
//     public int getMin() {
//         return minStack.peek();
//     }
// }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */