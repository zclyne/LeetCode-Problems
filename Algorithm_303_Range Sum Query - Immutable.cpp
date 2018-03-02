#include <iostream>
#include <vector>
using namespace std;
class NumArray {
public:
    NumArray(vector<int> nums) {
        int len=nums.size();
        num.push_back(0);
        for (int i=0;i<len;i++) num.push_back(num[i]+nums[i]);
    }
    
    int sumRange(int i, int j) {
        return num[j+1]-num[i];
    }
private:
    vector<int> num;
}; 
