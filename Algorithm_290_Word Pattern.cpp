#include <iostream>
#include <sstream>
using namespace std;
bool wordPattern(string pattern, string str)
{
	string map[26],cur;
	stringstream stream;
	int len=pattern.length();
	stream<<str;
	for (int i=0;i<26;i++) map[i]="@";
	for (int i=0;i<len;i++)
	{
		stream>>cur;
		if (map[pattern[i]-'a']=="@") map[pattern[i]-'a']=cur;
		else if (map[pattern[i]-'a']!=cur) return false;
	}
	if (!stream.eof()) return false;
	for (int i=0;i<25;i++)
	{
		for (int j=i+1;j<26;j++) if (map[i]!="@" && map[i]==map[j]) return false;
	}
	return true;
}
int main()
{
	string pattern="aaa";
	string str="aa aa aa";
	cout<<wordPattern(pattern,str);
	return 0;
}
