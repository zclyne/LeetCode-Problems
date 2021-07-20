import java.util.Stack;

// 方法1：栈
// 定义一个叫做槽位的概念:
// 如果遇到一个空节点，则要消耗一个当前栈顶节点的槽位
// 如果遇到一个非空节点，则在消耗了一个当前栈顶节点的槽位的同时，添加两个新的槽位
// 栈中的每个元素代表了对应节点剩余的槽位的数量，栈顶元素对应于下一步可用的槽位数量
// 当遇到空节点时，仅将栈顶元素减1；
// 当遇到非空节点时，将栈顶元素减1后，再向栈中压入一个2
// 无论何时，如果栈顶元素变为0，就立刻将栈顶弹出
// 遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列
// 否则若栈不为空，则序列不合法
// 此外，在遍历的过程中，若槽位数量不足，则序列不合法。

class Solution {
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack<Integer>();
        String[] nodes = preorder.split(",");
        int n = nodes.length;
        stack.push(1); // 根节点特殊处理
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                return false;
            }
            if ("#".equals(nodes[i])) { // 当前元素是null，消耗栈顶元素的一个槽位
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
            } else { // 当前元素不是null，消耗栈顶元素的一个槽位，再push一个2
                int top = stack.pop() - 1;
                if (top > 0) { // 仍然有槽位剩余，push回去
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }
}

// 方法2：计数
// 用一个变量slots来代替方法1的栈中所有元素之和

class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int n = nodes.length;
        int slots = 1;
        for (int i = 0; i < n; i++) {
            if (slots == 0) {
                return false;
            }
            if ("#".equals(nodes[i])){
                slots--;
            } else {
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }
}

// 方法3：DFS
// 模拟建树的过程

class Solution {
    private int pos = 0;
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        // 只有在nodes数组全部遍历完成时，才说明整棵树合法，所以要判断pos是否等于nodes.length
        return dfs(nodes) && pos == nodes.length;
    }
    public boolean dfs(String[] nodes) {
        // 递归有2个出口，越界则返回false，如果是空节点则返回true
        if (pos >= nodes.length) {
            return false;
        }
        if ("#".equals(nodes[pos])) {
            pos++;
            return true;
        }
        pos++;
        // 左右子树
        return dfs(nodes) && dfs(nodes);
    }
}

// 方法4：栈+重建整棵树，空间消耗比较大

class Solution {

    private class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidSerialization(String preorder) {
        if ("#".equals(preorder)) {
            return true;
        }
        String[] nodes = preorder.split(",");
        if ("#".equals(nodes[0])) {
            return false;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(new TreeNode(Integer.parseInt(nodes[0])));
        int idx = 1;

        while (!stack.isEmpty() && idx < nodes.length) {
            String val = nodes[idx];
            TreeNode curNode = stack.pop();
            // if curNode already has a left subnode and a right subnode, invalid
            if (curNode.left != null && curNode.right != null) {
                return false;
            }
            
            TreeNode newNode;
            if ("#".equals(val)) { // consider a null node as a node with val == -1, makes it easy to handle
                newNode = new TreeNode(-1);
            } else {
                newNode = new TreeNode(Integer.parseInt(val));
            }
            if (curNode.left == null) {
                curNode.left = newNode;
                stack.push(curNode);
            } else {
                curNode.right = newNode;
            }
            if (newNode.val != -1) { // is a real treeNode
                stack.push(newNode);
            }
            idx++;
        }

        return stack.isEmpty() && idx == nodes.length;
    }
}