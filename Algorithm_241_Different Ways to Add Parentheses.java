import java.util.ArrayList;
import java.util.List;

// 分治法：一个表达式可以表示成x op y的形式
// 其中，x和y各自也是一个表达式，op是运算符
// 所以对于expression的计算可以分解成x和y的计算，最后再通过op的不同计算最终的结果

class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();

        if ("".equals(expression)) {
            result.add(0);
            return result;
        }
        if (isNumber(expression)) {
            int num = Integer.parseInt(expression);
            result.add(num);
            return result;
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isOperation(c)) {
                List<Integer> leftNums = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightNums = diffWaysToCompute(expression.substring(i + 1));

                for (int leftNum : leftNums) {
                    for (int rightNum : rightNums) {
                        if (c == '+') {
                            result.add(leftNum + rightNum);
                        } else if (c == '-') {
                            result.add(leftNum - rightNum);
                        } else {
                            result.add(leftNum * rightNum);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}