#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        vector<string> res;
        if (s.size() < 10) return res;
        unordered_set<string> set, resSet;
        for (int i = 0 ; i <= s.size() - 10 ; i++)
        {
            string tmp = s.substr(i, 10);
            if (set.find(tmp) == set.end()) set.insert(tmp);
            else if (resSet.find(tmp) == resSet.end()) resSet.insert(tmp);
        }
        for (unordered_set<string>::iterator iter = resSet.begin(); iter != resSet.end() ; iter++) res.push_back(*iter);
        return res;
    }
};