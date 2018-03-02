#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };
 
ListNode* reverseList(ListNode* head)
{
	if (head==NULL || head->next==NULL) return head;
 	ListNode *parent=head,*cur=head->next;
 	if (cur->next==NULL)
 	{
 		cur->next=parent;
 		parent->next=NULL;
 		return cur;
	}
	ListNode *curNext=cur->next;
	parent->next=NULL;
	while (curNext)
	{
		cur->next=parent;
		parent=cur;
		cur=curNext;
		curNext=curNext->next;
	}
	cur->next=parent;
 	return cur;
}


/*ListNode* reverseList(ListNode* head) {
    ListNode *temp = NULL , *nextNode = NULL;
    while(head){
        nextNode = head->next;
        head->next = temp;
        temp = head;
        head = nextNode;
    }
    return temp;
}*/


int main()
{
	ListNode *n1=new ListNode(1);
	ListNode *n2=new ListNode(2);
	ListNode *n3=new ListNode(3);
	n1->next=n2;
	n2->next=n3;
	ListNode *tmp=reverseList(n1);
	while (tmp!=NULL)
	{
		cout<<tmp->val<<' ';
		tmp=tmp->next;
	}
}
