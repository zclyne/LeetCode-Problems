#include <iostream>
#include <sstream>
#include <vector>
using namespace std;
class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> result;
        for (int i=1;i<=n;i++)
        {
            if (i%3==0 && i%5!=0) result.push_back("Fizz");
            else if (i%5==0 && i%3!=0) result.push_back("Buzz");
            else if (i%3==0 && i%5==0) result.push_back("FizzBuzz");
            else
            {
                stringstream stream;
                string num;
                stream<<i;
                stream>>num;
                result.push_back(num);
            }
        }
        return result;
    }
};