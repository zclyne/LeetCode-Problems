#include <vector>
using namespace std;
class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int len=flowerbed.size();
        if (n>len) return false;
        if (flowerbed[0]==0 && flowerbed[1]==0)
        {
            n--;
            flowerbed[0]=1;
        }
        if (flowerbed[len-1]==0 && flowerbed[len-2]==0)
        {
            n--;
            flowerbed[len-1]=1;
        }
        for (int i=1;i<len-1;i++)
        {
            if (flowerbed[i-1] == 0 && flowerbed[i+1] == 0 && flowerbed[i] == 0)
            {
                n--;
                flowerbed[i]=1;
            }
        }
        return n<=0;
    }
};