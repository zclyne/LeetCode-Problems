#include <iostream>
using namespace std;
struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
void deleteNode(ListNode* node)
{
	ListNode *tmp=node,*parent;
	if (!tmp) return;
	while (tmp->next)
	{
		tmp->val=tmp->next->val;
		parent=tmp;
		tmp=tmp->next;
	}
	parent->next=NULL;
	delete tmp;
}
int main()
{
	ListNode *n1=new ListNode(1);
	ListNode *n2=new ListNode(2);
	ListNode *n3=new ListNode(3);
	ListNode *n4=new ListNode(4);
	n1->next=n2;
	n2->next=n3;
	n3->next=n4;
	deleteNode(n2);
	ListNode *tmp=n1;
	while (tmp)
	{
		cout<<tmp->val<<' ';
		tmp=tmp->next;
	}
}
