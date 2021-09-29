class Solution {
    public int calculate(String s) {
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        if (s.charAt(0) != '-') {
            s = "+" + s;
        }
        while (i < s.length()) {
            if (s.charAt(i) == ' ') { // skip spaces
                i++;
                continue;
            }
            char op = s.charAt(i++);
            while (s.charAt(i) == ' ') { // skip spaces
                i++;
            }
            int num = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
                i++;
            }
            if (op == '+') {
                stack.push(num);
            } else if (op == '-') {
                stack.push(-num);
            } else if (op == '*') {
                stack.push(stack.pop() * num);
            } else { // op == '/'
                stack.push(stack.pop() / num);
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}