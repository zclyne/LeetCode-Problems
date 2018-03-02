#include <iostream>
using namespace std;
// Forward declaration of guess API.
// @param num, your guess
// @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
int guess(int num);

class Solution {
public:
    int guessNumber(int n) {
        int low=1,high=n;
        if (!guess(n)) return n;
        int mid;
        while (true)
        {
            mid=(high-low)/2+low;
            if (guess(mid)==0) return mid;
            else if (guess(mid)==1) low=mid+1;
            else high=mid;
        }
    }
};