import java.util.Stack;

// Recursive Solution
// while只处理一对[]之间的内容，假设该[]之间的字符串已经decode完成，记为tmp，res就是以这对[]之前的数字作为次数，把tmp重复这么多次得到的字符串
// 若这对[]内部还有嵌套的[]，递归调用decodeString(..)

class Solution {
    private int i = 0;
    public String decodeString(String s) {
        String res = "";
        while (i < s.length() && s.charAt(i) != ']') {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count *= 10;
                    count += s.charAt(i++) - '0';
                }
                i++; // handle '['
                String tmp = decodeString(s);
                System.out.println(tmp);
                i++; // handle ']'
                while (count-- > 0) {
                    res += tmp;
                }
            } else {
                res += s.charAt(i++);
            }
        }
        return res;
    }
}



// Faster Stack Solution
// 栈中存储的是字符串而不是字符，从而节省了在遇到']'时不断pop所花费的时间

class FasterStackSolution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}



// My Second Solution, as slow as my first one

class MySecondSolution {
    public String decodeString(String s) {
        StringBuilder resString = new StringBuilder();
        Stack<Character> resStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count *= 10;
                    count += s.charAt(i) - '0';
                    i++;
                }
                countStack.push(count);
                count = 0;
                // now s.charAt(i) == '['
                resStack.push(s.charAt(i));
            } else if (s.charAt(i) == ']') {
                int repeatTime = countStack.pop();
                StringBuilder builder = new StringBuilder();
                while (resStack.peek() != '[') {
                    builder.append(resStack.pop());
                }
                resStack.pop(); // get rid of the '['
                char[] builderCharSequence = builder.reverse().toString().toCharArray();
                for (int j = 0; j < repeatTime; j++) {
                    for (char curChar : builderCharSequence) {
                        resStack.push(curChar);
                    }
                }
            } else {
                resStack.push(s.charAt(i));
            }
            i++;
        }
        while (!resStack.isEmpty()) {
            resString.append(resStack.pop());
        }
        return resString.reverse().toString();
    }
}




// My First Solution, beats 34.17%

class MyFirstSolution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(c);
            } else {
                StringBuilder builder = new StringBuilder();
                char top = stack.pop();
                while (top != '[') {
                    builder.append(top);
                    top = stack.pop();
                }
                int time = 0, pow = 1;
                top = stack.pop();
                while (true) {
                    time += pow * (top - '0');
                    pow *= 10;
                    if (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                        top = stack.pop();
                    } else {
                        break;
                    }
                }
                char[] builderCharSequence = builder.reverse().toString().toCharArray();
                for (int i = 0; i < time; i++) {
                    for (char curChar : builderCharSequence) {
                        stack.push(curChar);
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}