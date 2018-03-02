#include <iostream>
#include <vector>
#include <map>
using namespace std;
vector<string> findRestaurant(vector<string>& list1, vector<string>& list2)
{
    map<string,int> rest;
    int len1=list1.size(),len2=list2.size();
    for (int i=0;i<len1;i++) rest[list1[i]]=i;
    vector<string> res;
    int min=INT_MAX;
    for (int i=0;i<len2;i++) if (rest.find(list2[i])!=rest.end() && i+rest[list2[i]]<=min)
    {
        if (i+rest[list2[i]]<min) res.clear();
        res.push_back(list2[i]);
        min=i+rest[list2[i]];
    }
    return res;
}
int main()
{
    vector<string> s1,s2;
    s1.push_back("fuck");
    s1.push_back("shit");
    s2.push_back("shit");
    s2.push_back("fuck");
    vector<string> res=findRestaurant(s1,s2);
    for (int i=0;i<res.size();i++) cout<<res[i];
    return 0;
}