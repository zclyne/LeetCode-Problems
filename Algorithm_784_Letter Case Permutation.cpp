#include <vector>
#include <string>
using namespace std;
class Solution {
public:
    vector<string> res;
    void addString(string S, int i)
    {
        if (i==S.size())
        {
            res.push_back(S);
            return;
        }
        else if (!isalpha(S[i])) addString(S,i+1);
        else
        {
            addString(S,i+1);
            S[i]=S[i]>='a' && S[i]<='z'?char(S[i]-'a'+'A'):char(S[i]-'A'+'a');
            addString(S,i+1);
        }
    }
    vector<string> letterCasePermutation(string S) {
        addString(S,0);
        return res;
    }
};