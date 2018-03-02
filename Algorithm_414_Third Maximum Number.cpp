#include <vector>
#include <climits>
using namespace std;
class Solution {
public:
    void swap(long long int &a, long long int &b)
    {
        long long int tmp=a;
        a=b;
        b=tmp;
    }
    int thirdMax(vector<int>& nums) {
        int len=nums.size();
        if (len<3)
        {
            int max=INT_MIN;
            for (int i=0;i<len;i++) if (nums[i]>max) max=nums[i];
            return max;
        }
        else
        {
            long long int a = LLONG_MIN, b = LLONG_MIN, c = LLONG_MIN;
            for (int i=0;i<len;i++)
            {
                long long int tmp=nums[i];
                if (tmp <= c || tmp == b || tmp == a) continue;
                c=tmp;
                if (b<c) swap(b,c);
                if (a<b) swap(a,b);
            }
            return c == LLONG_MIN?a:c;
        }
    }
};