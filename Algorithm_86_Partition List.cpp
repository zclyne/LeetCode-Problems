#include <queue>
#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };
class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        ListNode *tmp=head;
        queue<int> less,greater;
        while (tmp)
        {
            if (tmp->val<x) less.push(tmp->val);
            else if (tmp->val>=x) greater.push(tmp->val);
            tmp=tmp->next;
        }
        ListNode *newHead=new ListNode(-1);
        tmp=newHead;
        while (!less.empty())
        {
            tmp->next=new ListNode(less.front());
            less.pop();
            tmp=tmp->next;
        }
        while (!greater.empty())
        {
            tmp->next=new ListNode(greater.front());
            greater.pop();
            tmp=tmp->next;
        }
        return newHead->next;
    }
};