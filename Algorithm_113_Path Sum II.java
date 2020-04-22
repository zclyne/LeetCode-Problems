// 思路：类似于前序遍历
// 注意函数退出前要把之前添加到cur末尾的root.val删除掉

class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return result;
        }
        preOrder(root, sum, new ArrayList<>());
        return result;
    }
    private void preOrder(TreeNode root, int sum, List<Integer> cur) {
        sum -= root.val;
        cur.add(root.val);
        if (root.left == null && root.right == null && sum == 0) {
            result.add(new ArrayList<>(cur));
        } else {
            if (root.left != null) {
                preOrder(root.left, sum, cur);
            }
            if (root.right != null) {
                preOrder(root.right, sum, cur);
            }
        }
        cur.remove(cur.size() - 1);
    }
}