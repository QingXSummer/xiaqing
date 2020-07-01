package com.java8.lambda.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述    :斐波纳契元组序列
 * Author :Qing_X
 * Date   :2019-08-30 10:16
 */
public class Feibonaqie {

    public static void main(String[] args) {
        feibonaqie();
    }

    public static void feibonaqie(){
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(20).forEach(t->System.out.println(t[0]+","+t[1]));
    }
}
