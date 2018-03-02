#include <iostream>
#include <vector>
#include <map>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    vector<int> findMode(TreeNode* root) {
        vector<int> res;
        if (!root) return res;
        map<int,int> frequency;
        int maxFre=0;
        find(root,frequency,maxFre,res);
        return res;
    }
    void find(TreeNode *root, map<int,int> &frequency, int &maxFre, vector<int> &res)
    {
        if (!root) return;
        if (++frequency[root->val]>maxFre)
        {
            maxFre=frequency[root->val];
            res.clear();
            res.push_back(root->val);
        }
        else if (frequency[root->val]==maxFre) res.push_back(root->val);
        find(root->left,frequency,maxFre,res);
        find(root->right,frequency,maxFre,res);
    }
};