#include <vector>
using namespace std;
class Solution {
public:
    int maxCount(int m, int n, vector<vector<int>>& ops) {
        if (ops.size()==0) return m*n;
        int min_a=INT_MAX,min_b=INT_MAX;
        for (int i=0;i<ops.size();i++)
        {
            int a=ops[i][0],b=ops[i][1];
            if (a<=0 || b<=0) continue;
            if (a<min_a) min_a=a;
            if (b<min_b) min_b=b;
        }
        return min_a*min_b;
    }
};