#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;
class Solution {
public:
    unordered_map<char,int> map;
    unordered_map<char,int> correctMap;
	bool isCorrect(string p)
	{
		for (int i = 0; i < p.size(); i++)
		{
			if (map[p[i]] != correctMap[p[i]]) return false;
		}
		return true;
	}
    vector<int> findAnagrams(string s, string p) {
        vector<int> result;
        if (s.size()<p.size()) return result;
        int count_correct=0;
		int sLen = s.size(), pLen = p.size();
		for (int i = 0; i < pLen; i++)
		{
			correctMap[p[i]]++;
			map[s[i]]++;
		}
		if (isCorrect(p)) result.push_back(0);
		for (int i = pLen; i < sLen; i++)
		{
			map[s[i - pLen]]--;
			map[s[i]]++;
			if (isCorrect(p)) result.push_back(i - pLen + 1);
        }
        return result;
    }
};
int main()
{
	string s = "abab";
	string p = "ab";
    Solution sol;
    vector<int> res=sol.findAnagrams(s,p);
    for (int i=0;i<res.size();i++) cout<<res[i]<<' ';
	cin.get();
	return 0;
}