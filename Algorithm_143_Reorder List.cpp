#include <iostream>
#include <stack>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution
{
  public:
    void reorderList(ListNode *head)
    {
        if (!head || !head->next) return;
        stack<ListNode *> sta;
        ListNode *cur = head, *toInsert;
        while (cur)
        {
            sta.push(cur);
            cur = cur->next;
        }
        int size = sta.size();
        cur = head;
        for (int i = size / 2; i > 0; i--)
        {
            toInsert = sta.top();
            sta.pop();
            toInsert->next = cur->next;
            cur->next = toInsert;
            cur = toInsert->next;
        }
        if (size % 2 == 1) cur->next = NULL;
        else toInsert->next = NULL;
    }
};