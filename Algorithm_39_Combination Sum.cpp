#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    vector<vector<int> > res;
    void dfs(vector<int> &candidates, int target, int idx, vector<int> tmpRes)
    {
        if (target<0) return;
        if (target==0)
        {
            res.push_back(tmpRes);
            return;
        }
        for (int i=idx;i<candidates.size();i++)
        {
            tmpRes.push_back(candidates[i]);
            dfs(candidates,target-candidates[i],i,tmpRes);
            tmpRes.pop_back();
        }
    }
    vector<vector<int> > combinationSum(vector<int>& candidates, int target) {
        dfs(candidates,target,0,{});
        return res;
    }
};