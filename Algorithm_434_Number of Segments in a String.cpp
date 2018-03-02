#include <sstream>
using namespace std;
class Solution {
public:
    int countSegments(string s) {
        s=' '+s;
        int count=0;
        for (int i=1;i<s.size();i++) if (s[i-1]==' ' && s[i]!=' ') count++;
        return count;
    }
};