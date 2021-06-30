import java.util.Arrays;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// 解法1：BFS
// 类似于二叉树的层序遍历

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuilder res = new StringBuilder();
        res.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node != null){
                res.append("" + node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }else{
                res.append("null");
            }
            res.append(",");
        }
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == ""){
            return null;
        }
        String[] dataList = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!"null".equals(dataList[i])){
                node.left = new TreeNode(Integer.parseInt(dataList[i]));
                queue.offer(node.left);
            }
            i++;
            if(!"null".equals(dataList[i])){
                node.right = new TreeNode(Integer.parseInt(dataList[i]));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
}

// 解法2：DFS
// 类似于二叉树的前序遍历
// 用队列来表示split后的数组，每构建出一个node，就从队列头上删除一个元素

public class Codec {

    private int deserializeIndex = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        this.dfs(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void dfs(TreeNode root, StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(",");
        }
        if (root == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append(root.val);
        this.dfs(root.left, stringBuilder);
        this.dfs(root.right, stringBuilder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        this.deserializeIndex = 0;
        return recoverTree(nodes);
    }

    private TreeNode recoverTree(String[] nodes) {
        if (this.deserializeIndex == nodes.length) {
            return null;
        }
        if (nodes[this.deserializeIndex] == null || nodes[this.deserializeIndex].equals("") || nodes[this.deserializeIndex].equals("null")) {
            this.deserializeIndex++; // move to the next node in nodes
            return null;
        }
        int val = Integer.parseInt(nodes[this.deserializeIndex]);
        TreeNode node = new TreeNode(val);
        this.deserializeIndex++;
        node.left = this.recoverTree(nodes);
        node.right = this.recoverTree(nodes);
        return node;
    }
}


// My Solution，超出内存限制
// 用数组保存每个节点的值，然后添加到string中

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        int length = this.getMaxLength(root, 1);
        String[] treeArr = new String[length];
        Arrays.fill(treeArr, "");
        this.fillArr(root, treeArr, 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(treeArr[i]);
            if (i < length - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }

    private int getMaxLength(TreeNode root, int curNo) {
        int leftMaxNo = 0, rightMaxNo = 0;
        if (root.left != null) {
            leftMaxNo = this.getMaxLength(root.left, 2 * curNo);
        }
        if (root.right != null) {
            rightMaxNo = this.getMaxLength(root.right, 2 * curNo + 1);
        }
        return Integer.max(curNo, Integer.max(leftMaxNo, rightMaxNo));
    }

    private void fillArr(TreeNode root, String[] treeArr, int curNo) {
        if (root == null) {
            return;
        }
        treeArr[curNo - 1] = String.valueOf(root.val);
        this.fillArr(root.left, treeArr, 2 * curNo);
        this.fillArr(root.right, treeArr, 2 * curNo + 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data) || data == null) {
            return null;
        }
        String[] treeArr = data.split(",");
        return this.recoverTree(treeArr, 1);
    }

    private TreeNode recoverTree(String[] treeArr, int curNo) {
        int index = curNo - 1;
        if (index >= treeArr.length || "".equals(treeArr[index])) {
            return null;
        }
        int val = Integer.parseInt(treeArr[index]);
        TreeNode node = new TreeNode(val);
        node.left = this.recoverTree(treeArr, 2 * curNo);
        node.right = this.recoverTree(treeArr, 2 * curNo + 1);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));