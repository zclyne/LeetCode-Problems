import java.util.HashMap;
import java.util.Map;

// 思路：后序遍历是先遍历左子树，再遍历右子树，最后输出根节点，所以把后序遍历的输出倒序，就等效于
// 先输出根节点，再遍历右子树，最后遍历左子树，类似于前序遍历
// 所以可以用前序遍历的思路来处理后序遍历的情况
// 后序遍历输出的最后一个元素值就是根节点的值，到inorder中寻找到该根节点的位置
// 该位置之前的输出就是左子树，之后的输出就是右子树
// 可以通过该位置前后的元素个数来得到下一层递归的后序遍历的开始和结束位置
// 为了防止每层递归都遍历inorder数组，先用map把数组中元素和其下标的对应关系用map存储起来

class Solution {

    // map stores the mapping relationship of values and its position in inorder array
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return reconstruct(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode reconstruct(int[] inorder, int inOrderStart, int inOrderEnd, int[] postorder, int postOrderStart, int postOrderEnd) {
        if (postOrderEnd < postOrderStart) {
            return null;
        }
        int rootVal = postorder[postOrderEnd];
        TreeNode root = new TreeNode(rootVal);
        // get the position of rootVal in inorder
        int inOrderCur = map.get(rootVal);
        int leftSubTreeNodeCount = inOrderCur - inOrderStart; // number of nodes in the left sub-tree
        int rightSubTreeNodeCount = inOrderEnd - inOrderCur; // number of nodes in the right sub-tree
        int rightPostOrderEnd = postOrderEnd - 1;
        int rightPostOrderStart = rightPostOrderEnd - rightSubTreeNodeCount + 1;
        int leftPostOrderEnd = rightPostOrderStart - 1;
        int leftPostOrderStart = leftPostOrderEnd - leftSubTreeNodeCount + 1;
        root.left = reconstruct(inorder, inOrderStart, inOrderCur - 1, postorder, leftPostOrderStart, leftPostOrderEnd);
        root.right = reconstruct(inorder, inOrderCur + 1, inOrderEnd, postorder, rightPostOrderStart, rightPostOrderEnd);
        return root;
    }
}