#include <iostream>
#include <cmath>
using namespace std;
class Solution {
public:
    bool judgeSquareSum(int c) {
        for (int a=0;a<=sqrt(c);a++)
        {
            int b=sqrt(c-a*a);
            if (a*a+b*b==c) return true;
        }
        return false;
    }
};