#include <vector>
#include <algorithm>
#include <unordered_map>
#include <set>
using namespace std;
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        /*if (nums.size()==0) return 0;
        sort(nums.begin(),nums.end());
        unordered_map<int,set<int> >map;
        int count=0;
        for (int i=0;i<nums.size()-1;i++)
        {
            for (int j=i+1;j<nums.size();j++)
            {
                if (nums[j]-nums[i]==k && map[nums[i]].find(nums[j])==map[nums[i]].end())
                {
                    map[nums[i]].insert(nums[j]);
                    count++;
                }
            }
        }*/
        if (nums.size()==0 || k<0) return 0;
        int count=0;
        unordered_map<int,int> map;
        if (k==0) for (int i=0;i<nums.size();i++) map[nums[i]]=-1;
        for (int i=0;i<nums.size();i++) map[nums[i]]++;
        for (int i=0;i<nums.size();i++)
        {
            if (map.find(nums[i]+k)!=map.end() && map[nums[i]+k]>0)
            {
                count++;
                map[nums[i]+k]=0;
            }
        }
        return count;
    }
};