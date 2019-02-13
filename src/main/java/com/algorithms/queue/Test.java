package com.algorithms.queue;

/**
 * Author :Qing_X
 * Date   :2019-01-28 21:57
 */
public class Test {
    public static void main(String[] args) {

        Integer[]m = new Integer[5];
        MaxPQ<Integer> maxPQ = new MaxPQ <>(m);
        maxPQ.add(1);
        maxPQ.add(11);
        maxPQ.add(134);
        maxPQ.add(23);
        maxPQ.add(1);
        maxPQ.add(213);
        maxPQ.add(43);
        maxPQ.add(65);
        maxPQ.add(21);
        maxPQ.add(2);
        maxPQ.add(4);
        System.out.println(maxPQ.toString());
    }
}
