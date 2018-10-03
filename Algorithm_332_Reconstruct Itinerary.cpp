#include <iostream>
#include <vector>
#include <unordered_map>
#include <set>
#include <stack>
using namespace std;
class Solution {
public:
    vector<string> findItinerary(vector<pair<string, string>> tickets) {
        unordered_map<string, multiset<string> > map;
        for (auto p : tickets) map[p.first].insert(p.second);
        vector<string> res;
        stack<string> sta;
        sta.push("JFK");
        while (!sta.empty())
        {
            string top = sta.top();
            if (!map[top].empty())
            {
                sta.push(*map[top].begin());
                map[top].erase(map[top].begin());
            }
            else
            {
                res.push_back(top);
                sta.pop();
            }
        }
        return vector<string>(res.rbegin(), res.rend());
    }
};