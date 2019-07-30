package com.java8.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 描述    :用于分离质数和合数的收集器
 * Author :Qing_X
 * Date   :2019-07-22 10:34
 */
public class PrimeNumCollector implements Collector <Integer, Map <Boolean, List <Integer>>, Map <Boolean, List <Integer>>> {

    public static void main(String[] args) {
        List<String> list = new ArrayList <>();
        list.add("a");
        list.add("b");
        list.add("c");
//        List<String> list1 = list.subList(1, 3);
        list.add("d");
//        System.out.println(list);
//        System.out.println(list1);
    }

    @Override
    public Supplier <Map <Boolean, List <Integer>>> supplier() {
        return () -> {
            HashMap <Boolean, List <Integer>> map = new HashMap <>();
            map.put(true, new ArrayList <>());
            map.put(false, new ArrayList <>());
            return map;
        };
    }

    @Override
    public BiConsumer <Map <Boolean, List <Integer>>, Integer> accumulator() {
        return (T, U) -> T.get(isPrime(T.get(true), U)).add(U);
    }

    @Override
    public BinaryOperator <Map <Boolean, List <Integer>>> combiner() {
        return null;
    }

    @Override
    public Function <Map <Boolean, List <Integer>>, Map <Boolean, List <Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set <Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate <A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
