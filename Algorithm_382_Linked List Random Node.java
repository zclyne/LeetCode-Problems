import java.util.Random;

class Solution {
    private int count;
    private ListNode head;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        count = 0;
        this.head = head;
        ListNode tmp = head;
        while (tmp != null)
        {
            count++;
            tmp = tmp.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random rand = new Random(); // use the system time(ms) as the seed
        int randInt = rand.nextInt(count); // generate a random integer with the upperbound as count(exclusive)
        ListNode tmp = head;
        while (randInt > 0)
        {
            tmp = tmp.next;
            randInt--;
        }
        return tmp.val;
    }
}

// reservoir sampling solution
public class Solution {

    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    ListNode head = null;
    Random randomGenerator = null;
    public Solution(ListNode head) {
        this.head = head;
        this.randomGenerator = new Random();

    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode result = null;
        ListNode current = head;
        
        for(int n = 1; current!=null; n++) {
            if (randomGenerator.nextInt(n) == 0) {
                result = current;
            }
            current = current.next;
        }
        
        return result.val;
        
    }
}