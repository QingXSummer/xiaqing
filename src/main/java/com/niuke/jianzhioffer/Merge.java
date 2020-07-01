package com.niuke.jianzhioffer;

/**
 * 描述    :输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * Author :Qing_X
 * Date   :2019-08-13 21:21
 */
public class Merge {

    public ListNode Merge(ListNode list1,ListNode list2) {
        Object o;
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        ListNode p1 ,p2;
        if(list1.val<=list2.val){
            p1= list1;
            p2=list2;
        }else{
            p1= list2;
            p2=list1;
        }
        while (p2!=null){
            while(p1!=null){
                if(p1.val<=p2.val){

                }
            }
        }
        return null;
    }
}
