// 方法：括号展开+栈
// 由于字符串除了数字与括号外，只有加号和减号两种运算符。因此，如果展开表达式中所有的括号，则得到的新表达式中，数字本身不会发生变化，只是每个数字前面的符号会发生变化。
// 因此，我们考虑使用一个取值为{-1,+1}的整数sign代表「当前」的符号。根据括号表达式的性质，它的取值：
//     与字符串中当前位置的运算符有关；
//     如果当前位置处于一系列括号之内，则也与这些括号前面的运算符有关：每当遇到一个以-号开头的括号，则意味着此后的符号都要被「翻转」
// 考虑到第二点，我们需要维护一个栈ops，其中栈顶元素记录了当前位置所处的每个括号所「共同形成」的符号。例如，对于字符串1+2+(3-(4+5))：
//     扫描到1+2时，由于当前位置没有被任何括号所包含，则栈顶元素为初始值+1，表示符号为正；
//     扫描到1+2+(3时，当前位置被一个括号所包含，该括号前面的符号为+号，因此栈顶元素依然是+1，表示符号为正；
//     扫描到1+2+(3-(4时，当前位置被两个括号所包含，分别对应着+号和-号，由于+号和-号合并的结果为−号，因此栈顶元素变为−1，表示符号为负。
// 在得到栈ops之后，sign的取值就能够确定了：如果当前遇到了+号，则更新sign ← ops.top()；如果遇到了遇到了-号，则更新sign ← −ops.top()。
// 然后，每当遇到(时，都要将当前的sign取值压入栈中；每当遇到)时，都从栈中弹出一个元素。这样，我们能够在扫描字符串的时候，即时地更新ops中的元素。
// https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-by-leetcode-solution-jvir/

class Solution {
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}