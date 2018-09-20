#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    bool wordBreak(string s, vector<string> &wordDict)
    {
        bool *valid = new bool[s.size()];
        for (int i = 0; i < s.size(); i++) valid[i] = false;
        for (int i = 0; i < s.size(); i++)
        {
            for (int j = 1; i + j <= s.size(); j++)
            {
                string word = s.substr(i, j);
                if (hasWord(word, wordDict)  && (i == 0 || i !=0 && valid[i - 1])) valid[i + j - 1] = true;
            }
        }
        return valid[s.size() - 1];
    }
    bool hasWord(string word, vector<string> &wordDict)
    {
        for (int i = 0; i < wordDict.size(); i++) if (wordDict[i] == word) return true;
        return false;
    }
};