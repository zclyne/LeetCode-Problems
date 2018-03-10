#include <iostream>
using namespace std;
class Solution {
public:
    int low=0, maxLen=1;
    string longestPalindrome(string s) {
        if (s.size()<2) return s;
        for (int i=0;i<s.size()-1;i++) getPalindrome(s,i);
        return s.substr(low,maxLen);
    }
    void getPalindrome(string s, int i)
    {
        int l,h;
        for (int k=0;k<2;k++)
        {
            if (k==0) {l=i;h=i;}
            else {l=i;h=i+1;}
             while (l>=0 && h<s.size() && s[l]==s[h]) {l--;h++;}
            l++;
            h--;
            if (h-l+1>maxLen)
            {
                low=l;
                maxLen=h-l+1;
            }
        }
    }
};