#include <iostream>
#include <unordered_set>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution
{
  public:
    ListNode *detectCycle(ListNode *head)
    {
        if (!head || !head->next) return NULL;
        unordered_set<ListNode *> set;
        ListNode *tmp = head;
        while (tmp)
        {
            if (set.find(tmp) == set.end()) set.insert(tmp);
            else return tmp;
            tmp = tmp->next;
        }
        return NULL;
    }
};

// another better solution
class Solution
{
    public:
    ListNode *detectCycle(ListNode *head)
    {
        if (!head || !head->next) return NULL;
        ListNode *p1 = head, *p2 = head;
        while (p1 && p1->next)
        {
            p1 = p1->next->next;
            p2 = p2->next;
            if (p1 == p2) break;
        }
        if (!p1 || !p1->next) return NULL;
        p1 = head;
        while (p1 != p2)
        {
            p1 = p1->next;
            p2 = p2->next;
            if (p1 == p2) return p1;
        }
        return p1;
    }
};