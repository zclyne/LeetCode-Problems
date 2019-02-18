// 思路：直接对左右子节点递归

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

// BFS Solution
// int maxDepth(TreeNode *root)
// {
//     if(root == NULL)
//         return 0;
    
//     int res = 0;
//     queue<TreeNode *> q;
//     q.push(root);
//     while(!q.empty())
//     {
//         ++ res;
//         for(int i = 0, n = q.size(); i < n; ++ i)
//         {
//             TreeNode *p = q.front();
//             q.pop();
            
//             if(p -> left != NULL)
//                 q.push(p -> left);
//             if(p -> right != NULL)
//                 q.push(p -> right);
//         }
//     }
    
//     return res;
// }