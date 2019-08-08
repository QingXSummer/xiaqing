package com.java.thread;

import java.util.*;
import java.util.concurrent.*;

/**
 * 描述    :
 * Author :QingX
 * Date   :2019-07-29 17:14
 */
public class QueueTest {
    public static void main(String[] args) {
        HashMap hashMap;
        Comparable comparable;
        Comparator comparator;
        ConcurrentHashMap concurrentHashMap;
        ConcurrentLinkedDeque linkedDeque;
        CyclicBarrier cyclicBarrier;
        CountDownLatch countDownLatch;
        Semaphore semaphore;
        Executor executor;
        ExecutorService service;
        Callable callable;
        Executors.callable(()->System.out.println());
        Executors.newFixedThreadPool(10);
        SynchronousQueue synchronousQueue;
        FutureTask futureTask;
        ThreadFactory threadFactory;
        LinkedList linkedList;
        LinkedBlockingQueue queue;
        ThreadLocal local;
        Iterator iterator;
        ListIterator listIterator;
        Collection collection;
        Collections collections;
        CopyOnWriteArrayList copyOnWriteArrayList;


    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<ListNode> stack = new Stack();
        while (listNode!=null){
            stack.add(listNode);
            listNode=listNode.next;
        }
        ArrayList<Integer> list = new ArrayList <>();
        while (!stack.empty()){
            list.add(stack.pop().val);
        }
        return list;
    }

         public  class ListNode {
             int val;
             ListNode next = null;

             ListNode(int val) {
                 this.val = val;
             }
         }
}
