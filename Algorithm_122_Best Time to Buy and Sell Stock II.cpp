#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int maxProfit(vector<int>& prices)
{
	int max=0;
	if (prices.size()<=1) return 0;
	for (int i=0;i<prices.size()-1;i++)
	{
		if (prices[i+1]>prices[i]) max+=prices[i+1]-prices[i];
	}
	return max;
}
