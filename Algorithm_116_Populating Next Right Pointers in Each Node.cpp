#include <iostream>
using namespace std;
struct TreeLinkNode {
 int val;
 TreeLinkNode *left, *right, *next;
 TreeLinkNode(int x) : val(x), left(NULL), right(NULL), next(NULL) {}
};
class Solution
{
  public:
    void connect(TreeLinkNode *root)
    {
        if (!root) return;
        if (root->left)
        {
            TreeLinkNode *LR = root->left;
            TreeLinkNode *RL = root->right;
            while (LR)
            {
                LR->next = RL;
                LR = LR->right;
                RL = RL->left;
            }
            connect(root->left);
            connect(root->right);
        }
    }
};

