#include <iostream>
#include <algorithm>
#include <unordered_map>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
// class Solution {
// public:
//     int rob(TreeNode* root) {
//         if (!root) return 0;
//         int leftSubSubTree = root->left ? rob(root->left->left) + rob(root->left->right) : 0;
//         int rightSububTree = root->right ? rob(root->right->left) + rob(root->right->right) : 0;
//         return max(root->val + leftSubSubTree + rightSububTree, rob(root->left) + rob(root->right));
//     }
// };

// improve the solution by using dp
// class Solution {
// private:
//     unordered_map<TreeNode *, int> map;
// public:
//     int rob(TreeNode* root) {
//         if (!root) return 0;
//         if (map.find(root) != map.end()) return map[root];
//         int leftSubSubTree = root->left ? rob(root->left->left) + rob(root->left->right) : 0;
//         int rightSububTree = root->right ? rob(root->right->left) + rob(root->right->right) : 0;
//         map[root] = max(root->val + leftSubSubTree + rightSububTree, rob(root->left) + rob(root->right));
//         return map[root];
//     }
// };

// the best solution
// pair.first stores the result if the root is not robbed, and pair.second stores the result if the root is robbed
class Solution {
public:
    int rob(TreeNode* root) {
        pair<int, int> res = robHouse(root);
        return max(res.first, res.second);
    }
    pair<int, int> robHouse(TreeNode *root)
    {
        if (!root) return make_pair(0, 0);
        pair<int, int> leftRes = robHouse(root->left), rightRes = robHouse(root->right), res;
        res.first = max(leftRes.first, leftRes.second) + max(rightRes.first, rightRes.second);
        res.second = root->val + leftRes.first + rightRes.first;
        return res;
    }
};