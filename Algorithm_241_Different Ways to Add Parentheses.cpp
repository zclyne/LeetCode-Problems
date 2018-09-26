#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
using namespace std;
class Solution
{
  public:
    vector<int> diffWaysToCompute(string input)
    {
        vector<int> result;
        for (int i = 0; i < input.size(); i++)
        {
            if (input[i] == '+' || input[i] == '-' || input[i] == '*')
            {
                vector<int> resultLeft = diffWaysToCompute(input.substr(0, i));
                vector<int> resultRight = diffWaysToCompute(input.substr(i + 1, input.size() - i - 1));
                for (int j = 0; j < resultLeft.size(); j++)
                {
                    for (int k = 0; k < resultRight.size(); k++)
                    {
                        if (input[i] == '+')
                            result.push_back(resultLeft[j] + resultRight[k]);
                        else if (input[i] == '-')
                            result.push_back(resultLeft[j] - resultRight[k]);
                        else
                            result.push_back(resultLeft[j] * resultRight[k]);
                    }
                }
            }
        }
        if (!result.size())
            result.push_back(stoi(input));
        return result;
    }
};

// better dp solution using unordered_map to avoid repeated computing
class Solution
{
  public:
    vector<int> diffWaysToCompute(string input)
    {
        unordered_map<string, vector<int>> map;
        return compute(input, map);
    }
    vector<int> compute(string input, unordered_map<string, vector<int>> &map)
    {
        if (map.find(input) != map.end())
            return map[input];
        vector<int> result;
        for (int i = 0; i < input.size(); i++)
        {
            if (input[i] == '+' || input[i] == '-' || input[i] == '*')
            {
                vector<int> resultLeft = compute(input.substr(0, i), map);
                vector<int> resultRight = compute(input.substr(i + 1, input.size() - i - 1), map);
                for (int j = 0; j < resultLeft.size(); j++)
                {
                    for (int k = 0; k < resultRight.size(); k++)
                    {
                        if (input[i] == '+')
                            result.push_back(resultLeft[j] + resultRight[k]);
                        else if (input[i] == '-')
                            result.push_back(resultLeft[j] - resultRight[k]);
                        else
                            result.push_back(resultLeft[j] * resultRight[k]);
                    }
                }
            }
        }
        if (!result.size())
            result.push_back(stoi(input));
        map[input] = result;
        return result;
    }
};