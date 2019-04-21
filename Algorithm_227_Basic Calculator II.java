import java.util.Deque;
import java.util.Stack;
import java.util.LinkedList;

// My Solution
// 思路：用两个栈numStack和charStack
// 读入数字时，存入numStack；读入'+'或'-'时，存入charStack；读入'*'或'/'时，再向后读入一个数字，然后弹出numStack的栈顶元素与其做相应运算后压回栈中
// 一次遍历完成后，所有的乘除运算都已完成，再遍历一遍charStack完成加减运算
// 最终numStack中所剩的唯一一个元素即答案
// 注意必须使用deque而不能使用stack，因为做加减运算时，要按照从前往后的顺序来运算，不能从后向前

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> charStack = new LinkedList<>();
        char[] s_arr = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s_arr[i])) {
                int curNum = 0;
                while (i < s.length() && Character.isDigit(s_arr[i])) {
                    curNum = curNum * 10 + s_arr[i] - '0';
                    i++;
                }
                numStack.offerLast(curNum);
            } else if (s_arr[i] == '*' || s_arr[i] == '/') {
                char operator = s_arr[i];
                while (i < s.length() && s_arr[++i] == ' ') {}
                int lastNum = numStack.pollLast();
                int curNum = 0;
                while (i < s.length() && Character.isDigit(s_arr[i])) {
                    curNum = curNum * 10 + s_arr[i] - '0';
                    i++;
                }
                numStack.offerLast(operator == '*' ? lastNum * curNum : lastNum / curNum);
            } else if (s_arr[i] == '+' || s_arr[i] == '-') {
                charStack.offerLast(s_arr[i++]);
            } else {
                i++;
            }
        }
        while (!charStack.isEmpty()) {
            char operator = charStack.pollFirst();
            int num1 = numStack.pollFirst(), num2 = numStack.pollFirst();
            numStack.offerFirst(operator == '+' ? num1 + num2 : num1 - num2);
        }
        return numStack.peekFirst();
    }
}

// Better Solution
// 思路：sign存储上一次访问到的运算符

class BetterSolution {
    public int calculate(String s) {
        int len;
        if(s == null || (len = s.length()) == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < len; i++){
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1){
                if(sign == '-'){
                    stack.push(-num);
                }
                if(sign == '+'){
                    stack.push(num);
                }
                if(sign == '*'){
                    stack.push(stack.pop() * num);
                }
                if(sign == '/'){
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
    
        int res = 0;
        for(int i : stack){
            res += i;
        }
        return res;
    }
}

// Another Solution, no stack

class AnotherSolution {
    public int calculate(String s) {
        if (s == null) return 0;
        s = s.trim().replaceAll(" +", ""); // important
        int length = s.length();
        
        int res = 0;
        long preVal = 0; // initial preVal is 0
        char sign = '+'; // initial sign is +
        int i = 0;
        while (i < length) {
            long curVal = 0;
            while (i < length && Character.isDigit(s.charAt(i))) { // int
                curVal = curVal * 10 + s.charAt(i) - '0';
                i++;
            }
            if (sign == '+') {
                res += preVal;  // update res
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;  // update res
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
            } else if (sign == '/') {
                preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
            }
            if (i < length) { // getting new sign
                sign = s.charAt(i);
                i++;
            }
        }
        res += preVal;
        return res;
    }
}