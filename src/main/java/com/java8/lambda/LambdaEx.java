package com.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-07-12 10:21
 */
public class LambdaEx {
    public static void main(String[] args) {
        List <String> list = new ArrayList <>();
        list.add("123");
        list.add("532");
        list.add("126");
        list.sort((String::compareTo));

        List <String> streams = list.stream().map(str -> str.split(""))
                .flatMap(Arrays::stream).distinct()
                .collect(toList());
        streams.forEach(System.out::println);

        Comparator comparator;
        Comparable comparable;
        Predicate <Object> predicate;
        Supplier <Object> supplier;
        Function <Object, Object> function;
        BiFunction <Object, Object, Object> biFunction;
        BinaryOperator operator;
        Stream stream;
        UnaryOperator unaryOperator;
        Collector collector;
    }
}
