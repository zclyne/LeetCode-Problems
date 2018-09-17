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
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        if (!inorder.size()) return NULL;
        if (inorder.size() == 1) return new TreeNode(inorder[0]);
        int entry;
        for (entry = 0; entry < inorder.size(); entry++) if (inorder[entry] == postorder[postorder.size() - 1]) break;
        vector<int> newLeftInOrder, newRightInOrder, newLeftPostOrder, newRightPostOrder;
        for (int i = 0; i < entry; i ++)
        {
            newLeftInOrder.push_back(inorder[i]);
            newLeftPostOrder.push_back(postorder[i]);
        }
        for (int i = entry + 1; i < inorder.size(); i++)
        {
            newRightInOrder.push_back(inorder[i]);
            newRightPostOrder.push_back(postorder[i-1]);
        }
        TreeNode *root = new TreeNode(postorder[postorder.size() - 1]);
        root->left = buildTree(newLeftInOrder, newLeftPostOrder);
        root->right = buildTree(newRightInOrder, newRightPostOrder);
        return root;
    }
};