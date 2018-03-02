#include <iostream>
#include <unordered_map>
using namespace std;
class Solution {
public:
    int longestPalindrome(string s) {
        int len=0, odd_max=0;
        bool hasOdd=false;
        if (s.size()==0) return 0;
        unordered_map<char,int> map;
        for (int i=0;i<s.size();i++) map[s[i]]++;
        for (unordered_map<char,int>::iterator iter=map.begin();iter!=map.end();iter++)
        {
            if (iter->second%2==0) len+=iter->second;
            else
            {
                len+=iter->second-1;
                hasOdd=true;
            }
        }
        return hasOdd?len+1:len;
    }
};