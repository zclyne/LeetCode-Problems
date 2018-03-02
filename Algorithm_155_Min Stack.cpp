#include <iostream>
#include <vector>
using namespace std;
class MinStack {
private:
	vector<int> sta;
	int len;
public:
    /** initialize your data structure here. */
    MinStack() {
        len=0;
    }
    
    void push(int x) {
        sta.push_back(x);
        len++;
    }
    
    void pop() {
    	if (len==0) return;
        sta.erase(sta.begin()+len-1);
        len--;
    }
    
    int top() {
        if (len>=1) return sta[len-1];
    }
    
    int getMin() {
    	int min=sta[0];
        for (int i=1;i<len;i++) if (sta[i]<min) min=sta[i];
		return min; 
    }
};
