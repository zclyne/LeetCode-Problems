#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
ListNode* removeElements(ListNode* head, int data) {
    if (head==NULL) return NULL;
    while (head!=NULL && head->val==data)
    {
        ListNode* deleted = head;
        head = head->next;
        delete deleted;
    }
    ListNode *tmp,*deleted;
    tmp=head;
    while (tmp!=NULL && tmp->next!=NULL)
    {
        if (tmp->next->val==data)
        {
            deleted=tmp->next;
            tmp->next=tmp->next->next;
            delete deleted;
        }
        else
        tmp=tmp->next;
    }
    return head;
}
int main()
{
	ListNode *n1=new ListNode(1);
	ListNode *n2=new ListNode(2);
	ListNode *n3=new ListNode(3);
	n1->next=n2;
	n2->next=n3;
	n1=removeElements(n1,3);
	ListNode *tmp=n1;
	while (tmp!=NULL)
	{
		 cout<<tmp->val<<' ';
		 tmp=tmp->next;
	}
}
