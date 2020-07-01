package com.niuke.jianzhioffer;

/**
 * 描述    : 输入一个链表，输出该链表中倒数第k个结点。
 * Author :Qing_X
 * Date   :2019-08-13 18:41
 */
public class FindKthToTail {
    public ListNode findKthToTail(ListNode head,int k) {
        ListNode p1=head;
        ListNode p2=head;
        int c = 0;
        while (p2.next!=null){
            if(c>=k-1) {
                p1=p1.next;
            }
            p2=p2.next;
            c++;
        }
        if(c<k-1) return null;
        return p1;
    }
}



class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}