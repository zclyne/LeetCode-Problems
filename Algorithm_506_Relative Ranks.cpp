#include <vector>
#include <queue>
#include <unordered_map>
#include <sstream>
using namespace std;
class Solution {
public:
    vector<string> findRelativeRanks(vector<int>& nums) {
        priority_queue<int> pq;
        unordered_map<int, int> map;
        for (int i=0;i<nums.size();i++)
        {
            pq.push(nums[i]);
            map[nums[i]]=i;
        }
        vector<string> result;
        result.resize(nums.size());
        for (int i=0;i<nums.size();i++)
        {
            int cur=pq.top();
            pq.pop();
            int index=map[cur];
            if (i==0) result[index]="Gold Medal";
            else if (i==1) result[index]="Silver Medal";
            else if (i==2) result[index]="Bronze Medal";
            else
            {
                string tmp;
                stringstream stream;
                stream<<i+1;
                stream>>tmp;
                result[index]=tmp;
            }
        }
        return result;
    }
};