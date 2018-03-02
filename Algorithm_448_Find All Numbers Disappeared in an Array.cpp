#include <vector>
#include <cmath>
using namespace std;
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        /*unordered_map<int,int> map;
        vector<int> result;
        for (int i=0;i<nums.size();i++) map[nums[i]]++;
        for (int i=1;i<=nums.size();i++)
        {
            if (map.find(i) == map.end()) result.push_back(i);
        }
        return result;*/
        vector<int> result;
        for (int i=0;i<nums.size();i++)
        {
            int index=abs(nums[i])-1;
            nums[index]=nums[index]>0?-nums[index]:nums[index];
        }
        for (int i=0;i<nums.size();i++) if (nums[i]>0) result.push_back(i+1);
        return result;
    }
};