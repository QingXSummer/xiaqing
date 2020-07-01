package com.java.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-12-24 23:17
 */
public class CompareTest {

    public static void main(String[] args) {

        Comparator comparator;
        Comparable comparable;

        List<Integer> list = new ArrayList <>();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(7);

        Collections.sort(list);

        for (int i : list){
            System.out.println(i);
        }

    }
}
