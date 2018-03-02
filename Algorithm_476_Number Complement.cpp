#include <iostream>
using namespace std;
class Solution {
public:
    string getBinary(int num)
    {
        if (num==0) return "0";
        string result="";
        while (num!=0)
        {
            int thisBit=num%2;
            num/=2;
            result=char(thisBit+48)+result;
        }
        return result;
    }
    int findComplement(int num) {
        string bin=getBinary(num);
        int len=bin.size(), base=1, result=0;
        for (int i=len-1;i>=0;i--)
        {
            if (bin[i]=='0') result+=base;
            base*=2;
        }
        return result;
    }
};