package com.java.basic;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class GenericType<k extends Comparable <k>> {
    public static void main(String[] args) {
        Comparable comparable;
        Comparator comparator;
        File[] files = new File("E:\\资料").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".pdf");
            }
        });
        int len = files.length;
        for (int i = 0; i < len; i++) {
            System.out.println(files[i]);
        }
    }


}

