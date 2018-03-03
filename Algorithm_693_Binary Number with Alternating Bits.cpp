#include <iostream>
using namespace std;
class Solution {
public:
    string toBinary(int n)
    {
        string res;
        while (n!=0)
        {
            int cur=n%2;
            n/=2;
            res=char(48+cur)+res;
        }
        return res;
    }
    bool hasAlternatingBits(int n) {
        string bin=toBinary(n);
        for (int i=0;i<bin.size()-1;i++) if (bin[i]==bin[i+1]) return false;
        return true;
    }
};