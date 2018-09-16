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
    struct node {
        int level;
        TreeNode *n;
        node(TreeNode *nod, int l) : level(l), n(nod) {}
    };
    vector<vector<int> > levelOrder(TreeNode* root) {
        vector<vector<int> > result;
        if (!root) return result;
        queue<node> q;
        node head(root, 0);
        int count = 0;
        q.push(head);
        vector<int> tmpRes;
        while (!q.empty())
        {
            node top = q.front();
            q.pop();
            if (top.level != count)
            {
                result.push_back(tmpRes);
                count = top.level;
                tmpRes.clear();
            }
            tmpRes.push_back(top.n->val);
            if (top.n->left)
            {
                node tmp(top.n->left, top.level + 1);
                q.push(tmp);
            } 
            if (top.n->right)
            {
                node tmp(top.n->right, top.level + 1);
                q.push(tmp);
            }
        }
        if (tmpRes.size()) result.push_back(tmpRes);
        return result;
    }
};