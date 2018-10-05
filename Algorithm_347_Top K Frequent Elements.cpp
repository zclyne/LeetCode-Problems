#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <queue>
using namespace std;
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> freqs;
        for (int i = 0; i < nums.size(); i++) freqs[nums[i]]++;
        vector<unordered_set<int> > res(nums.size() + 1);
        vector<int> toReturn;
        for (int i = 0; i < nums.size(); i++)
        {
            int pos = freqs[nums[i]];
            res[pos].insert(nums[i]);
        }
        for (int i = res.size() - 1; i >= 0; i--)
        {
            if (!res[i].empty())
            {
                unordered_set<int>::iterator iter = res[i].begin();
                while (iter != res[i].end() && k > 0)
                {
                    k--;
                    toReturn.push_back(*iter);
                    iter++;
                }
            }
        }
        return toReturn;
    }
};

// better solution
class Compare
{
public:
    bool operator()(const pair<int, int>& lhs, const pair<int, int>& rhs) {
        return lhs.second < rhs.second;
    }
};
class Solution {    
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        for(auto n : nums) {
            ++mp[n];
        }
        priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> q;
        for(auto i : mp) {
            q.push(i);
        }
        vector<int> ans;
        for(int i = 0; i < k; ++i) {
            ans.push_back(q.top().first);
            q.pop();
        }
        return ans;
    }
};