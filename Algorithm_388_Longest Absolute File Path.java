import java.util.Stack;

// 方法：首先以\n为分界，将input字符串split成String[] lines，每一个元素就是一行
// 创建栈stack，并push一个0到stack中，表示初始状态下，最外层的path的长度为0，是空字符串
// 然后遍历lines。记当前行为line
// 首先找到line中最后一个\t出现的下标，把这个下标值+1记为深度depth，最外层深度为0
// 原本的深度为栈的大小stack.size() - 1
// 记depthDiff = stack.size() - 1 - depth，则depthDiff就是要弹栈的次数
// 如果当前的depth正好等于stack.size() - 1，说明这一行所代表的的文件和目录恰好在上一行所表示的目录下，因此不需要弹栈
// line.substring(depth)就是当前行所表示的文件或目录的名字
// 若当前行代表的是一个文件，则通过stack.peek()取出外层目录的长度，再加上文件名本身的长度，maxLen取较大值
// 若当前行代表的是一个目录，则通过stack.peek()取出外层目录的长度，加上当前目录的长度path.length()，再加1，代表路径中的'/'符号，并压入栈中

class Solution {
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        String[] lines = input.split("\n");

        for (String line : lines) {
            int depth = line.lastIndexOf('\t') + 1;
            int depthDiff = stack.size() - 1 - depth;
            while (depthDiff > 0) {
                stack.pop();
                depthDiff--;
            }

            String path = line.substring(depth);
            if (isFile(path)) {
                maxLen = Math.max(maxLen, stack.peek() + path.length());
            } else {
                // + 1 for the '/' after the directory
                stack.push(stack.peek() + path.length() + 1);
            }
        }

        return maxLen;
    }

    private boolean isFile(String path) {
        return path.indexOf('.') != -1;
    }
}