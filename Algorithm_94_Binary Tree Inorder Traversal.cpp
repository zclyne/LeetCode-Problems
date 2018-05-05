#include <vector>
#include <stack>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

// class Solution {
// public:
//     vector<int> inorderTraversal(TreeNode* root) {
//         vector<int> res;
//         inorder(root, res);
//         return res;
//     }
//     void inorder(TreeNode *root, vector<int> &res)
//     {
//         if (!root) return;
//         inorder(root->left, res);
//         res.push_back(root->val);
//         inorder(root->right, res);
//     }
// };

class Solution {
public:
    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> res;
        if (!root) return res;
        stack<TreeNode *> sta;
        stack<int> cnt;
        sta.push(root);
        cnt.push(0);
        while (!sta.empty())
        {
            TreeNode *tmp=sta.top();
            int count=cnt.top();
            cnt.pop();
            sta.pop();
            if (count==0)//the first time
            {
                cnt.push(1);
                sta.push(tmp);
                if (tmp->left)
                {
                    sta.push(tmp->left);
                    cnt.push(0);
                }
            }
            else//the second time
            {
                res.push_back(tmp->val);
                if (tmp->right)
                {
                    sta.push(tmp->right);
                    cnt.push(0);
                }
            }
        }
        return res;
    }
};