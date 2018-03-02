#include <vector>
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
    struct layer
    {
        int num=0;
        long long int sum=0;
    };
    void sum(TreeNode *root, int thisLayer)
    {
        if (!root) return;
        map[thisLayer].num++;
        map[thisLayer].sum+=root->val;
        if (root->left) sum(root->left, thisLayer+1);
        if (root->right) sum(root->right, thisLayer+1);
    }
    unordered_map<int,layer> map; 
    vector<double> averageOfLevels(TreeNode* root) {
        vector<double> result;
        sum(root,0);
        for (int i=0;map.find(i)!=map.end();i++) result.push_back(double(map[i].sum)/map[i].num);
        return result;
    }
};