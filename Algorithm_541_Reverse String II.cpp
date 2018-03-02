#include <iostream>
using namespace std;
class Solution {
public:
    string reverseStr(string s, int k) {
        int len=s.size();
        for (int i=0;i<len;i+=2*k)
        {
            if (len-i>=k)//more than 2k characters left
            {
                for (int j=0;j<k/2;j++)
                {
                    char tmp=s[i+j];
                    s[i+j]=s[i+k-j-1];
                    s[i+k-j-1]=tmp;
                }
            }
            else//less than k characters left
            {
                for (int j=0;j<(len-i)/2;j++)
                {
                    char tmp=s[i+j];
                    s[i+j]=s[len-j-1];
                    s[len-j-1]=tmp;
                }
            }
        }
        return s;
    }
};