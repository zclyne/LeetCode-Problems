#include <vector>
#include <unordered_set>
using namespace std;
class Solution {
public:
    int distributeCandies(vector<int>& candies) {
        unordered_set<int> s;
        for (int i=0;i<candies.size();i++) s.insert(candies[i]);
        return candies.size()/2>s.size()?s.size():candies.size()/2;
    }
};