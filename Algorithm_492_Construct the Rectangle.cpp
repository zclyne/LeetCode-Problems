#include <vector>
#include <cmath>
#include <climits>
using namespace std;
class Solution {
public:
    vector<int> constructRectangle(int area) {
        int sqr=sqrt(area);
        vector<int> result;
        for (int i=sqr; i>0; i--)
        {
            if (area%i==0)
            {
                result.push_back(area/i);
                result.push_back(i);
                return result;
            }
        }
    }
};