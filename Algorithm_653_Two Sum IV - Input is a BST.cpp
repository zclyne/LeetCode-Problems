#include <iostream>
#include <unordered_map>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    unordered_map<int,int> map;
    void addToMap(TreeNode *root)
    {
        if (!root) return;
        map[root->val]++;
        if (root->left) addToMap(root->left);
        if (root->right) addToMap(root->right);
    }
    bool findTarget(TreeNode* root, int k) {
        addToMap(root);
        for (unordered_map<int,int>::iterator iter=map.begin();iter!=map.end();iter++)
        {
            if (map.find(k-iter->first)!=map.end())
            {
                if (k-iter->first!=iter->first) return true;
                else if (map[iter->first]>=2) return true;
            }
        }
        return false;
    }
};