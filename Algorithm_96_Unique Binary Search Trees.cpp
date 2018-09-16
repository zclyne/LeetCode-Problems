#include <iostream>
using namespace std;
class Solution {
public:
    int numTrees(int n) {
        if (n == 1) return 1;
        int *arr = new int[n+1]();
        arr[0] = arr[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            for (int j = 0; j <= i-1 ; j++) arr[i] += arr[j] * arr[i-1-j];
        }
        return arr[n];
    }
};