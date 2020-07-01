package com.java8.lambda;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-07-19 11:54
 */
public class StreamTest {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrime(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(
                "Fastest execution done in " + fastest + " msecs");
    }


    public static boolean isPrime(int num) {
        int num_1 = (int) Math.sqrt(num);
        return IntStream.rangeClosed(2, num_1).noneMatch(n -> num % n == 0);
    }

    public static Map <Boolean, List <Integer>> partitionPrime(int num) {
        return IntStream.rangeClosed(2, num).boxed()
                .collect(Collectors.partitioningBy(i -> isPrime(i)));
    }

    public static Map <Boolean, List <Integer>> partitionPrimeSelf(int num) {
        return IntStream.rangeClosed(2, num).boxed()
                .collect(new PrimeNumCollector());
    }
}
