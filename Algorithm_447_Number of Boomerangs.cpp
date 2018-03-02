#include <vector>
#include <unordered_map>
using namespace std;
class Solution {
public:
    int numberOfBoomerangs(vector<pair<int, int>>& points) {
        unordered_map<int, int> map;
        int len=points.size();
        int count=0;
        for (int i=0;i<len;i++)
        {
            for (int j=0;j<len;j++)
            {
                if (j==i) continue;
                int squared_distance=(points[i].first-points[j].first)*(points[i].first-points[j].first)
                    +(points[i].second-points[j].second)*(points[i].second-points[j].second);
                if (map.find(squared_distance) == map.end()) map[squared_distance]+=1;
                else
                {
                    count+=2*map[squared_distance];
                    map[squared_distance]++;
                }
            }
            map.clear();
        }
        return count;
    }
};