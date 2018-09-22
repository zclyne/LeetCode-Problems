#include <iostream>
#include <vector>
#include <queue>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    vector<int> rightSideView(TreeNode* root) {
        vector<int> res;
        if (!root) return res;
        queue<TreeNode *> nodeQueue;
        queue<int> level;
        int lastLevel = -1;
        nodeQueue.push(root);
        level.push(0);
        while (!nodeQueue.empty())
        {
            TreeNode *curNode = nodeQueue.front();
            nodeQueue.pop();
            int curLevel = level.front();
            level.pop();
            if (curLevel != lastLevel) // is the rightmost node in this level, add it to res
            {
                lastLevel = curLevel;
                res.push_back(curNode->val);
            }
            if (curNode->right)
            {
                nodeQueue.push(curNode->right);
                level.push(curLevel + 1);
            }
            if (curNode->left)
            {
                nodeQueue.push(curNode->left);
                level.push(curLevel + 1);
            }
        }
        return res;
    }
};