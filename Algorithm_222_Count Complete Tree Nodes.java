/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// 方法：二分查找+位运算
// 规定根节点位于第0层，完全二叉树的最大层数为h。根据完全二叉树的特性，其最左边的节点一定位于最底层，即h层
// 当0 <= i < h时，第i层包含2^i个节点。最底层包含的节点数最少为1，最多为2^h
// 当最底层包含1个节点时，完全二叉树的总节点个数是2^h
// 当最底层包含2^h个节点时，完全二叉树的总节点个数是2^(h + 1) - 1
// 因此对于最大层数为h的完全二叉树，节点个数一定在区间[2^h, 2(h + 1) - 1]范围内，可以使用二分查找来找到确切的个数
// 具体做法是，根据节点个数范围的上下界得到当前需要判断的节点个数k，如果第k个节点存在，则节点个数一定大于或等于k
// 如果第k个节点不存在，则节点个数一定小于k，由此可以将查找的范围缩小一半，直到得到节点个数
// 如果第k个节点位于第h层，则k的二进制表示包含h+1位，其中最高位是1，其余各位从高到低表示从根节点到第k个节点的路径
// 0表示移动到左子节点，1表示移动到右子节点。通过位运算得到第k个节点对应的路径，判断该路径对应的节点是否存在，即可判断第k个节点是否存在。

// https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // get the hight of the tree
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }

        // binary search
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (bits > 0) {
            if ((bits & k) == 0) { // go to the left sub-node
                node = node.left;
            } else { // go to the right sub-node
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}


// 方法2
// 对root的左右字数分别计算高度，记为left和right
// 如果left == right，说明左子树一定是满二叉树，因为节点已经填充到右子树了，左子树节点个数即为2^left - 1
// 总节点个数 = 1 + 2^left - 1 + countNodes(root.right)。1表示root节点本身
// 如果left > right，说明左子树不是满二叉树，而右子树是满二叉树
// 总节点个数 = 1 + 2^right - 1 + countNodes(root.left)。1表示root节点本身，2^right - 1表示右子树的总节点个数
// https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/

class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}