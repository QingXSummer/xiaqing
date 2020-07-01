package com.niuke.jianzhioffer;

/**
 * 描述    :输入一个链表，反转链表后，输出新链表的表头。
 * Author :Qing_X
 * Date   :2019-08-13 19:32
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next=new ListNode(0);
        head.next.next=new ListNode(1);
        head.next.next.next=new ListNode(2);
        ListNode t = new ReverseList().ReverseList(head);
        System.out.println(t.val);
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;
        ListNode p1 = head.next;
        if (p1 == null) return head;
        ListNode p2 = p1.next;
        p1.next = head;
        head.next = null;
        if (p2 == null) {
            return p1;
        }
        while (p2 != null) {
            ListNode pp = p1;
            p1 = p2;
            p2 = p2.next;
            p1.next = pp;
        }
        return p1;
    }

}

