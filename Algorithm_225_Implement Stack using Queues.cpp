#include <queue>
using namespace std;
class MyStack {
public:
    queue<int> que;
	// Push element x onto stack.
	void push(int x) {
        que.push(x);
	}

	// Removes the element on top of the stack.
	void pop() {
        for (int i=0;i<que.size()-1;i++)
        {
            que.push(que.front());
            que.pop();
        }
        que.pop();
	}

	// Get the top element.
	int top() {
        for (int i=0;i<que.size()-1;i++)
        {
            que.push(que.front());
            que.pop();
        }
        int res=que.front();
        que.pop();
        que.push(res);
        return res;
	}

	// Return whether the stack is empty.
	bool empty() {
        return que.empty();
	}
};

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * bool param_4 = obj.empty();
 */