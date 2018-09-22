#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;
class Solution {
public:
    int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int pow_m = pow(2, int(log2(m))), pow_n = pow(2, int(log2(n)));
        return pow_m != pow_n ? 0 : pow_m | rangeBitwiseAnd(m - pow_m, n - pow_n);
    }
};

// better solution
// key idea: if n > m, the lowest bit must be 0
int rangeBitwiseAnd(int m, int n) {
    return (n > m) ? (rangeBitwiseAnd(m >> 1, n >> 1) << 1) : m;
}