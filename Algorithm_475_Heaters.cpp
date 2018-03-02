#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int findRadius(vector<int>& houses, vector<int>& heaters)
    {
        if (heaters.size()==0) return 0;
        int size=houses.size();
        int max=0,cur=0,index=0;
        sort(houses.begin(),houses.end());
        sort(heaters.begin(),heaters.end());
        for (int i=0;i<size;i++)
        {
            while (index<heaters.size()-1 && abs(heaters[index+1]-houses[i])<=abs(heaters[index]-houses[i])) index++;
            cur=abs(heaters[index]-houses[i]);
            if (cur>max) max=cur;
        }
        return max;
    }
};
int main()
{
	vector<int> houses,heaters;
	houses.push_back(1);
	houses.push_back(2);
	houses.push_back(3);
	houses.push_back(4);
	houses.push_back(5);
	heaters.push_back(1);
	heaters.push_back(2);
	heaters.push_back(3);
	heaters.push_back(4);
	heaters.push_back(5);
	cout<<findRadius(houses, heaters);
	return 0;
}
