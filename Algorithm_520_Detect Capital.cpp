#include <iostream>
using namespace std;
class Solution {
public:
    bool detectCapitalUse(string word) {
        int len=word.length();
        if (len==0) return false;
        if (len==1) return true;
        bool first,second;
        if (word[0]>='A' && word[0]<='Z') first=true;
        else first=false;
        if (word[1]>='A' && word[1]<='Z') second=true;
        else second=false;
        if (!first)//lower
        {
            for (int i=1;i<len;i++)
            {
                if (word[i]>='A' && word[i]<='Z') return false;
            }
            return true;
        }
        else//upper
        {
            if (second)
            {
                for (int i=2;i<len;i++) if (word[i]>='a' && word[i]<='z') return false;
                return true;
            }
            else
            {
                for (int i=2;i<len;i++) if (word[i]>='A' && word[i]<='Z') return false;
                return true;
            }
        }
    }
};