#include <iostream>
#include <vector>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        if (!inorder.size()) return NULL;
        if (inorder.size() == 1) return new TreeNode(preorder[0]);
        int entry;
        for (entry = 0; entry < inorder.size(); entry++) if (inorder[entry] == preorder[0]) break;
        TreeNode *root = new TreeNode(preorder[0]);
        vector<int> leftInOrder, rightInOrder, newLeftPreOrder, newRightPreOrder;
        for (int i = 0 ; i < entry ; i++) leftInOrder.push_back(inorder[i]);
        for (int i = entry + 1 ; i < inorder.size() ; i++) rightInOrder.push_back(inorder[i]);
        for (int i = 1; i < 1 + entry ; i++) newLeftPreOrder.push_back(preorder[i]);
        for (int i = entry + 1; i < preorder.size(); i++) newRightPreOrder.push_back(preorder[i]);
        root->left = buildTree(newLeftPreOrder, leftInOrder);
        root->right = buildTree(newRightPreOrder, rightInOrder);
        return root;
    }
};