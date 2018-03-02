#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());
        int child=0, cookie=0, content=0;
        while (child < g.size() && cookie < s.size())
        {
            if (s[cookie] < g[child])//cannot be satisfied
            {
                cookie++;
            }
            else//can be satisfied
            {
                cookie++;
                child++;
                content++;
            }
        }
        return content;
    }
};