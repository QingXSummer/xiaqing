package com.java8.lambda.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述    :数值流应用：勾股数
 * Author :Qing_X
 * Date   :2019-08-30 08:41
 */
public class Gougu {

    public static void main(String[] args) {
        gougu2();
    }

    public static void gougu1() {
        Stream <int[]> mm = IntStream.rangeClosed(1, 100).boxed().flatMap(
                a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
        );
        mm.forEach(a -> System.out.println(StringUtils.join(a[0], "-", a[1], "-", a[2])));
    }

    public static void gougu2() {
        Stream <double[]> mm = IntStream.rangeClosed(1, 100).boxed().flatMap(
                a -> IntStream.rangeClosed(a, 100)
                        .boxed().map(b -> new double[]{(double) a, (double)b,  Math.sqrt(a * a + b * b)})
        ).filter(t -> t[2] % 1 == 0);
        mm.forEach(a -> System.out.println(StringUtils.join(a[0], "-", a[1], "-", a[2])));
    }
}
