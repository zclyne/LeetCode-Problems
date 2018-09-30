#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
class Solution {
public:
    int maxProduct(vector<string>& words) {
        if (!words.size() || words.size() == 1) return 0;
        int maxLen = 0;
        vector<bool> tmp(26, false);
        vector<vector<bool> > validTable(words.size(), tmp);
        for (int i = 0; i < words.size(); i++) for (auto c : words[i]) validTable[i][c - 'a'] = true;
        for (int i = 0; i < words.size() - 1; i++)
        {
            string curWord = words[i];
            for (int j = i + 1; j < words.size(); j++)
            {
                bool isValid = true;
                for (auto c : curWord)
                    if (validTable[j][c - 'a'])
                    {
                        isValid = false;
                        break;
                    }
                if (isValid && curWord.size() * words[j].size() > maxLen) maxLen = curWord.size() * words[j].size();
            }
        }
        return maxLen;
    }
};

// better bit manipulation solution
class Solution {
public:
    int maxProduct(vector<string> words)
    {
        if (words.size() == 0 || words.size() == 1) return 0;
        int maxLen = 0;
        vector<int> value(words.size(), 0);
        for (int i = 0; i < words.size(); i++)
            for (int j = 0; j < words[i].size(); j++)
                value[i] |= 1 << (words[i][j] - 'a');
        for (int i = 0; i < words.size() - 1; i++)
            for (int j = i + 1; j < words.size(); j++)
                if ((value[i] & value[j]) == 0 && (words[i].size() * words[j].size() > maxLen))
                    maxLen = words[i].size() * words[j].size();
        return maxLen;
    }
};