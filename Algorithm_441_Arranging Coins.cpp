#include <iostream>
using namespace std;
class Solution {
public:
    int arrangeCoins(int n) {
       int count=0,i=1;
       while (n-i>=0)
       {
           n-=i;
           count++;
           i++;
       }
       return count;
    }
};