#include <iostream>
#include <vector>
#include <stack>
using namespace std;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
  public:
    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    bool isInteger() const;

    // Return the single integer that this NestedInteger holds, if it holds a single integer
    // The result is undefined if this NestedInteger holds a nested list
    int getInteger() const;

    // Return the nested list that this NestedInteger holds, if it holds a nested list
    // The result is undefined if this NestedInteger holds a single integer
    const vector<NestedInteger> &getList() const;
};
class NestedIterator {
private:
    stack<NestedInteger> nestedStack;
    void buildStack(NestedInteger curNest)
    {
        if (curNest.isInteger()) nestedStack.push(curNest);
        else
        {
            vector<NestedInteger> curVec = curNest.getList();
            for (int i = curVec.size() - 1; i >= 0; i--)
            buildStack(curVec[i]);
        }
    }
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) buildStack(nestedList[i]);
    }
    int next() {
        int res = nestedStack.top().getInteger();
        nestedStack.pop();
        return res;
    }
    bool hasNext() {
        return !nestedStack.empty();
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */

// better java solution without recursion
// public class NestedIterator implements Iterator<Integer> {
//     Stack<NestedInteger> stack = new Stack<>();
//     public NestedIterator(List<NestedInteger> nestedList) {
//         for(int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         return stack.pop().getInteger();
//     }

//     @Override
//     public boolean hasNext() {
//         while(!stack.isEmpty()) {
//             NestedInteger curr = stack.peek();
//             if(curr.isInteger()) {
//                 return true;
//             }
//             stack.pop();
//             for(int i = curr.getList().size() - 1; i >= 0; i--) {
//                 stack.push(curr.getList().get(i));
//             }
//         }
//         return false;
//     }
// }