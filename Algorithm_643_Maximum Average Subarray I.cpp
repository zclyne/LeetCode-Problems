#include <vector>
#include <climits>
using namespace std;
class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        int len=nums.size();
        int sum=0;
        for (int i=0;i<k;i++) sum+=nums[i];
        int maxsum=sum;
        for (int i=1;i+k<=len;i++)
        {
            sum-=nums[i-1];
            sum+=nums[i+k-1];
            if (sum>maxsum) maxsum=sum;
        }
        return double(maxsum)/k;
    }
};