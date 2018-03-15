#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    vector<vector<int> >res;
    void dfs(vector<int> &candidates, int target, int idx, vector<int> tmpRes)
    {
        if (target==0)
        {
            res.push_back(tmpRes);
            return;
        }
        for (int i=idx;i<candidates.size() && target>=candidates[i];i++)
        {
            if (i>idx && candidates[i]==candidates[i-1]) continue;
            tmpRes.push_back(candidates[i]);
            dfs(candidates,target-candidates[i],i+1,tmpRes);
            tmpRes.pop_back();
        }
    }
    vector<vector<int> > combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(),candidates.end());
        dfs(candidates,target,0,{});
        return res;
    }
};