package com.java8.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2020-06-25 21:35
 */
public class MapStreamTest {

    public static void main(String[] args) {
        test2();


    }

    //给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,5]，应该返回[1, 4, 9, 16, 25]。
    public static void test1() {
        List <Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list = list.stream().map(a -> a * a).collect(Collectors.toList());
        System.out.println(list);
    }

    //给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
    //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
    //表数对。
    public static void test2() {
        List <Integer> list1 = Arrays.asList(1, 2, 3);
        List <Integer> list2 = Arrays.asList(4, 5);
        List <int[]> list3 = list1.stream().flatMap(
                i ->
                        (list2.stream().map(j -> (new int[]{i, j})))
        )
                .collect(Collectors.toList());
        System.out.println(list3.size());
    }


    //如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
    public static void test3() {
        List <Integer> list1 = Arrays.asList(1, 2, 3);
        List <Integer> list2 = Arrays.asList(4, 5);
        List <int[]> list3 = list1.stream().flatMap(i ->
                        (list2.stream()
                                .filter(j->((i+j))%3==0)
                                .map(j -> (new int[]{i, j})))
        )
                .collect(Collectors.toList());
        System.out.println(list3.size());
    }
}
