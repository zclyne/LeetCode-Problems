#include <iostream>
#include <vector>
using namespace std;
string longestCommonPrefix(vector<string>& strs);
int main()
{
	vector<string> strs;
	string a="abcde",b="abc",c="abe";
	strs.push_back(a);
	strs.push_back(b);
	strs.push_back(c);
	cout<<longestCommonPrefix(strs);
	return 0;
}
string longestCommonPrefix(vector<string>& strs)
{
	int cur=0;
    if (strs.size()==0) return "";
    bool flag=true;
    while(flag)
	{
		if (cur>strs[0].length()) return strs[0].substr(0,cur);
        for (int i=1;i<strs.size();i++)
        {
            if (cur>strs[i].length()) return strs[0].substr(0,cur);
            if (strs[i][cur]!=strs[0][cur]) flag=false;
        }
        if (flag) cur++;
    }
    return strs[0].substr(0,cur);
}
