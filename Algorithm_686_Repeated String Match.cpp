#include <iostream>
using namespace std;
class Solution {
public:
    int repeatedStringMatch(string A, string B) {
        if (A.find(B)!=string::npos) return 1;
        int len1=A.size(),len2=B.size();
        if (len1>=len2)
        {
            A+=A;
            return (A.find(B)==string::npos)?-1:2;
        }
        int count=1;
        string tmp=A;
        while (tmp.size()<len2)
        {
            tmp+=tmp;
            count*=2;
        }
        if (tmp.find(B)!=string::npos)
        {
            for (count=len2;count<=tmp.size();count++)
            {
                tmp-=B;
            }
        }
    }
};