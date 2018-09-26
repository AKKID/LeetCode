package List;

import java.util.*;

public class Solution {
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
//        n1.next.next = new ListNode(3);
        Solution s = new Solution();
        ListNode from = n1;
        ListNode to = n1.next;
        while(n1!=null){
            System.out.print(n1.val + "\t");
            n1 = n1.next;
        }
        s.reverse(from,from);
        System.out.println();
        while(to!=null){
            System.out.print(to.val + "\t");
            to = to.next;
        }



    }
    /***********************82***************************/
    // Using two pointers. cur to traverse the list and org to be the first list that
    // its value is different from before. If cur's value is different from org then add org
    // otherwise move cur to the first ListNode which's value is different from org. And move org to
    // that list too.
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode tHead = dummy, org = head, cur = head.next;
        while(cur != null){
            if(cur.val != org.val){
                tHead.next = org;
                tHead = tHead.next;
                org = cur;
                cur = org.next;
                if(cur == null){
                    tHead.next = org;
                    tHead = tHead.next;
                }
            }else{
                while(cur != null && cur.val == org.val){
                    cur = cur.next;
                }
                if(cur != null){
                    if(cur.next == null){
                        tHead.next = cur;
                        tHead = tHead.next;
                    }
                    org = cur;
                    cur = org.next;
                }
            }
        }
        tHead.next = null;
        return dummy.next;
    }
    /***********************61***************************/
    // Actually we can use fast and slow pointer to do it in one pass
    // but I do it by first count the length of the list. And then I
    // concat the list to a circle. Finally, I cut up the circle at count - k.
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;

        int count = 0;
        ListNode tmp = head;
        ListNode pre = tmp;
        while(tmp != null){
            pre = tmp;
            tmp = pre.next;
            count++;
        }
        pre.next = head;
        k = k % count;
        if(k == 0){
            pre.next = null;
            return head;
        }
        int b = count - k;
        pre = head;
        tmp = pre;
        int c = 0;
        while(c < b){
            pre = tmp;
            tmp = pre.next;
            c++;
        }
        pre.next = null;
        head = tmp;
        return head;
    }
    /***********************25***************************/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode from = head;
        ListNode tmp = head;
        int count = 0;
        while(count < k){
            tmp = tmp.next;
            count++;
        }
        reverse(from,tmp);
        ListNode newHead = tmp;
        ListNode tHead = tmp;
        while(tmp != null){
            count = 0;
            from = tHead.next;
            tmp = from;
            while(count < k && tmp != null){
                tmp = tmp.next;
            }
            if(tmp == null)
                break;
            reverse(from,tmp);
            tHead.next = tmp;
            tHead = from;

        }
        return newHead;
    }

    public void reverse(ListNode from, ListNode to){
        if(from == to)
            return;
        ListNode cur = from.next;
        ListNode head = from;
        ListNode tail = from;
        head.next = null;
        while(true){
            ListNode post = cur.next;
            cur.next = head;
            head = cur;
            cur = post;
            if(head == to)
                break;
        }
        tail.next = cur;
    }

    /***********************23***************************/
    // Using PriorityQueue
    public static ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(int i = 0; i < lists.length;i++){
            if(lists[i] != null)
                queue.add(lists[i]);
        }
        ListNode dummy = new ListNode(0);
        ListNode tmp = dummy;
        while(!queue.isEmpty()){
            ListNode cur = queue.poll();
            if(cur.next != null)
                queue.add(cur.next);
            tmp.next = cur;
            tmp = tmp.next;

        }
        return dummy.next;
    }

    /***********************19***************************/
    // First find the length of the list
    // and then delete length - n th node in list
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode tmp = head;
        while(tmp!=null){
            tmp = tmp.next;
            count++;
        }
        int k = count - n;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        count = 0;
        while(count < k){
            pre = cur;
            cur = pre.next;
            count++;
        }
        if(cur.next == null){
            cur = null;
            return dummy.next;
        }
        pre.next = cur.next;
        return dummy.next;
    }


}
class compare implements Comparator<ListNode> {
    @Override
    public int compare(ListNode l1, ListNode l2){
        return l1.val - l2.val;
//        if(l1.val > l2.val)
//            return 1;
//        else
//            return -1;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}