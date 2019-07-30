package com.java8.lambda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-07-22 19:52
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTime1 = dateTime.plusMonths(12);
        System.out.println(dateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
