#include <vector>
using namespace std;
class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& nums, int r, int c) {
        int old_row=nums.size();
        int old_col=nums[0].size();
        if (old_row * old_col != r * c) return nums;//illegal r and c
        vector<vector<int> > res;
        res.resize(r);
        for (int i=0;i<r;i++) res[i].resize(c);
        int count=0;
        while (count<r*c)
        {
            for (int i=0;i<r;i++)
            {
                for (int j=0;j<c;j++)
                {
                    int row=count/old_col;
                    int col=count%old_col;
                    res[i][j]=nums[row][col];
                    count++;
                }
            }
        }
        return res;
    }
};