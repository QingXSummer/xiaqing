package com.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-07-22 18:40
 */
public class OptionalTest {

    public static void main(String[] args) {
        List list = null;
        Optional<List> optionalList = Optional.ofNullable(list);
        System.out.println(optionalList);
    }
}
