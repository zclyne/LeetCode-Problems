#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* oddEvenList(ListNode* head) {
        if (!head || !head->next || !head->next->next) return head;
        ListNode *oddCur = head, *evenCur = head->next, *oddNext = evenCur->next, *evenHead = head->next;
        while (oddNext && oddNext->next)
        {
            oddCur->next = oddNext;
            evenCur->next = oddNext->next;
            oddCur = oddNext;
            evenCur = oddNext->next;
            oddNext = oddNext->next->next;
        }
        evenCur->next = NULL;
        if (oddNext)
        {
            oddCur->next = oddNext;
            oddNext->next = evenHead;
        }
        else oddCur->next = evenHead;
        return head;
    }
};