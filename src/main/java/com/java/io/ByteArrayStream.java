package com.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-11-26 00:18
 */
public class ByteArrayStream {

    public static void main(String[] args) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("I am xiaqing".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    }
}
