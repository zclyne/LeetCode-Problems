// In-Order Traversal Solution
// 思路：二叉查找树的中序遍历就是一个有序数组，所以只要找出中序遍历的输出中的两个顺序出错的元素，就是要交换的两个元素
// 注意中序遍历解法的空间复杂度不是O(1)，因为是递归

class Solution {

    private TreeNode first;
    private TreeNode second;
    private TreeNode prev;

    public void recoverTree(TreeNode root) {
        this.inOrderTraverse(root);

        // swap first and second
        int tempVal = this.first.val;
        this.first.val = this.second.val;
        this.second.val = tempVal;
    }

    public void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        if (this.prev != null && this.prev.val > root.val) { // not in order
            if (this.first == null) {
                this.first = this.prev;
                this.second = root; // first and second may be adjacent in the in-order traverse
            } else {
                this.second = root; // first and second are not adjacent, update second
            }
        }
        this.prev = root;
        inOrderTraverse(root.right);
    }
}

// Morris Traversal Solution
// Morris Traversal详解：https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
// 思路：Morris Traversal实际上也是中序遍历，但是只需要O(1)的空间复杂度
// 注意区分代码中的temp和prev

public class MorrisTraversalSolution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, prev = null;
        // in-order traverse the tree using Morris traversal
        TreeNode temp = root; // temp is used to find the previous node of root node in in-order traversal, it is different from prev
        while (root != null) {
            if (root.left == null) {
                if (prev != null && prev.val >= root.val) {
                    if (first == null) {
                        first = prev;
                    }
                    second = root;
                }
                prev = root;
                root = root.right;
            } else {
                temp = root.left;
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }
                if (temp.right == null) { // meet temp for the first time, connect root to the right sub-node of temp
                    temp.right = root;
                    root = root.left;
                } else { // temp.right == root
                    if (prev != null && prev.val > root.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = root;
                    }
                    temp.right = null;
                    prev = root;
                    root = root.right;
                }
            }
        }
        // swap first and second
        int tempVal = first.val;
        first.val = second.val;
        second.val = tempVal;
    }
}