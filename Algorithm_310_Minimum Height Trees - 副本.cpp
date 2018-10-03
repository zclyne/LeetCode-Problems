#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
using namespace std;
class Solution {
public:
    vector<int> findMinHeightTrees(int n, vector<pair<int, int>>& edges) {
        vector<int> res;
        if (n == 1) {res.push_back(0); return res;}
        unordered_set<int> tmpSet;
        vector<unordered_set<int> > graph(n, tmpSet);
        for (int i = 0; i < edges.size(); i++)
        {
            graph[edges[i].first].insert(edges[i].second);
            graph[edges[i].second].insert(edges[i].first);
        }
        queue<int> leaves;
        for (int i = 0; i < n; i++) if (graph[i].size() == 1) leaves.push(i);
        while (n > 2)
        {
            int size = leaves.size();
            n -= size;
            for (int i = 0; i < size; i++)
            {
                int cur = leaves.front();
                leaves.pop();
                unordered_set<int>::iterator iter = graph[cur].begin();
                graph[*iter].erase(cur);
                if (graph[*iter].size() == 1) leaves.push(*iter); // new leaf
            }
        }
        while (!leaves.empty()) {res.push_back(leaves.front()); leaves.pop();}
        return res;
    }
};