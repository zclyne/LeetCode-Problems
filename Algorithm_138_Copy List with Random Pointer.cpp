#include <iostream>
using namespace std;

struct RandomListNode {
    int label;
    RandomListNode *next, *random;
    RandomListNode(int x) : label(x), next(NULL), random(NULL) {}
};
class Solution
{
  public:
    RandomListNode *copyRandomList(RandomListNode *head)
    {
        if (!head) return NULL;
        RandomListNode *tmp = head, *res, *newNode;
        while (tmp)
        {
            RandomListNode* inserted = new RandomListNode(tmp->label);
            inserted->next = tmp->next;
            tmp->next = inserted;
            tmp = inserted->next;
        }
        tmp = head;
        while (tmp)
        {
            if (tmp->random) tmp->next->random = tmp->random->next;
            tmp = tmp->next->next;
        }
        tmp = head;
        res = head->next;
        while (tmp)
        {
            newNode = tmp->next;
            tmp->next = tmp->next->next;
            if (newNode->next) newNode->next = newNode->next->next;
            else tmp->next = NULL;
            tmp = tmp->next;
        }
        return res;
    }
};