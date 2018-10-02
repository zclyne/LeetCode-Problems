#include <iostream>
using namespace std;
class Solution {
public:
    bool isValidSerialization(string preorder) {
        int diff = 1;
        for (int i = 0; i < preorder.size(); i++)
        {
            if (preorder[i] == ',') continue;
            if (i == 0 || preorder[i - 1] == ',')
            {
                if (--diff < 0) return false;
                if (preorder[i] != '#') diff += 2;
            }
        }
        return diff == 0;
    }
};

// another java stack solution
// public class Solution {
//     public boolean isValidSerialization(String preorder) {
//         // using a stack, scan left to right
//         // case 1: we see a number, just push it to the stack
//         // case 2: we see #, check if the top of stack is also #
//         // if so, pop #, pop the number in a while loop, until top of stack is not #
//         // if not, push it to stack
//         // in the end, check if stack size is 1, and stack top is #
//         if (preorder == null) {
//             return false;
//         }
//         Stack<String> st = new Stack<>();
//         String[] strs = preorder.split(",");
//         for (int pos = 0; pos < strs.length; pos++) {
//             String curr = strs[pos];
//             while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
//                 st.pop();
//                 if (st.isEmpty()) {
//                     return false;
//                 }
//                 st.pop();
//             }
//             st.push(curr);
//         }
//         return st.size() == 1 && st.peek().equals("#");
//     }
// }