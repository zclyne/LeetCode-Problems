#include <iostream>
#include <vector>
#include <string>
using namespace std;
// class Solution {
// public:
//     vector<string> restoreIpAddresses(string s)
//     {
//         vector<string> ret;
//         string ans;
//         for (int a=1; a<=3; a++)
//         for (int b=1; b<=3; b++)
//         for (int c=1; c<=3; c++)
//         {
//             int d=s.length()-a-b-c;
//             int A = stoi(s.substr(0, a));
//             int B = stoi(s.substr(a, b));
//             int C = stoi(s.substr(a+b, c));
//             int D = stoi(s.substr(a+b+c, d));
//             if (A<=255 && B<=255 && C<=255 && D<=255)
//                 if ( (ans=to_string(A)+"."+to_string(B)+"."+to_string(C)+"."+to_string(D)).length() == s.length()+3)
//                     ret.push_back(ans);
//         }
//         return ret;
//     }
// };

class Solution {
public:
    vector<string> restoreIpAddresses(string s)
    {
        vector<string> sol;
        string res="";
        dfs(s,0,0,sol,res);
        return sol;
    }
    void dfs(string s, int k, int start, vector<string> &sol, string result)
    {
        if (k==4)
        {
            if (start==s.length())
            {
                result=result.substr(1,result.length()-1);
                sol.push_back(result);
            }
            return;
        }
        for (int i=0;i<3;i++)
        {
            if (start+i>=s.length()) break;
            else if (i>0 && s[start]=='0') continue;//leading zero
            else if (i<2 || stoi(s.substr(start,i+1))<256) dfs(s,k+1,start+i+1,sol,result+"."+s.substr(start,i+1));
        }
        return;
    }
};