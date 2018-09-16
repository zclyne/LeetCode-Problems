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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
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
                
                count = top.level;
                if (count % 2 == 0) 
                {
                    vector<int> reversed;
                    for (int i = tmpRes.size() - 1; i >= 0; i--) reversed.push_back(tmpRes[i]);
                    result.push_back(reversed);
                    reversed.clear();
                }
                else result.push_back(tmpRes);
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
        if (tmpRes.size() && count % 2 == 0) result.push_back(tmpRes);
        else 
        {
            vector<int> reversed;
            for (int i = tmpRes.size() - 1; i >= 0; i--) reversed.push_back(tmpRes[i]);
            result.push_back(reversed);
        }
        return result;
    }
};