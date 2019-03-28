import java.util.Deque;
import java.util.LinkedList;

// 思路：用split()方法把path根据'/'拆开，存入数组contents
// 根据contents中每个content的内容来决定是什么都不做、返回上级目录还是进入下一级目录

class Solution {
    public String simplifyPath(String path) {
        String[] contents = path.split("/");
        StringBuilder builder = new StringBuilder();
        builder.append('/');
        for (String content : contents) {
            if (content.equals(".") || content.equals("")) { // do nothing
                continue;
            } else if (content.equals("..")) { // move the directory up a level if not at root level now
                if (builder.length() > 1) {
                    builder.deleteCharAt(builder.length() - 1); // delete the last '/'
                    for (int i = builder.length() - 1; i > 0 && builder.charAt(i) != '/'; i--) {
                        builder.deleteCharAt(i);
                    }
                }
            } else { // directory name
                builder.append(content + "/");
            }
        }
        if (builder.length() > 1) { // delete the last '/'
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}



// Stack Solution
// 思路相同

class StackSolution {
    public String simplifyPath(String path) {
        StringBuilder builder = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        String[] contents = path.split("/");
        for (String content : contents) {
            if (content.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else if (!(content.equals("") || content.equals("."))) {
                stack.push(content);
            }
        }
        for(String content : stack) { // the traversal begins from the top of the stack
            builder.insert(0, "/" + content);
        }
        return builder.length() == 0 ? "/" : builder.toString();
    }
}