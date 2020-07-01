package com.java.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class IoTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\test\\test1.txt");
//        FileInputStream inputStream = new FileInputStream(file);
//        InputStreamReader reader = new InputStreamReader(inputStream);
//        char[]msg = new char[1024];
//        int count=-1;
//        while((count=reader.read(msg))>0){
//            System.out.println(count);
//        }

    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
