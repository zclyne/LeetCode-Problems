// 思路：flatten的过程本质上是preorder traversal，此处要求in-place，因此要自底向上进行
// 假定右子树已经flatten完毕，则所要做的操作即为把右子树添加到左子树最右边的子节点的right上
// 用prev来记录上一次递归时的根节点，考虑该例子：
//     1
//    / \
//   2   5
//  / \   \
// 3   4   6
// prev的变化顺序为：null, 6, 5, 4, 3, 2, 1

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {

    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // pay attention that we have to recursively call flatten() on right, then on left
        // the order is important
        flatten(root.right); // recursively reach the right-most node
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

// Iterative Solution
class Solution2 {
    public void flatten(TreeNode root) {
      while(root != null){
          if(root.left != null){
              TreeNode prev = root.left;
              while(prev.right != null){
                  prev = prev.right;
              }
              prev.right = root.right;
              root.right = root.left;
              root.left = null;
          }
          root = root.right;
      }
    }
}