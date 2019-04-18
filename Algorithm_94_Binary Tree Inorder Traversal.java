import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Iterative Solution
// 思路：用两个栈分别存储节点和对应于该节点的一个计数值
// 若第一次遇到当前节点（curCount == 1），此时该节点不能加入res，重新入栈，计数器-1
// stack.push(curNode), count.push(0)
// 在访问该节点前，要先对其左子树做遍历
// 因此，stack.push(curNode.left), count.push(1)
// 若第二次遇到当前节点(curCount == 0)，此时已经可以使用该节点
// res.add(curNode.val)，然后把该节点的右子树加入栈中
// stack.push(curNode.right), count.push(1)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        stack.push(root);
        count.push(1);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            int curCount = count.pop();
            if (curNode != null) {
                if (curCount == 0) { // the 2nd time we meet this node
                    res.add(curNode.val);
                    stack.push(curNode.right);
                    count.push(1);
                } else { // curCount == 1
                    stack.push(curNode);
                    count.push(0);
                    stack.push(curNode.left);
                    count.push(1);
                }
            }
        }
        return res;
    }
}

// Another Iterative Solution

class AnotherSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}

// Recursive Solution
// 思路：直接中序遍历，先左子树，再根节点，再右子树

// class Solution {
    
//     List<Integer> res = new ArrayList<>();

//     public List<Integer> inorderTraversal(TreeNode root) {
//         inorder(root);
//         return res;
//     }

//     public void inorder(TreeNode root) {
//         if (root == null) {
//             return;
//         }
//         inorder(root.left);
//         res.add(root.val);
//         inorder(root.right);
//     }

// }