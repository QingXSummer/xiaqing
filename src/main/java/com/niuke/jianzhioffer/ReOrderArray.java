package com.niuke.jianzhioffer;

import java.util.Arrays;

/**
 * 描述    :输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * Author :Qing_X
 * Date   :2019-08-13 17:27
 */
public class ReOrderArray {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7};
        new ReOrderArray().reOrderArray(a);
        System.out.println(Arrays.toString(a));
    }

    public void reOrderArray(int[] array) {
        int len = array.length;
        int[] newA = new int[len];
        int cur = 0;
        for (int i = 0; i < len; i++) {
            if ((array[i] & 1) != 0) {
                newA[cur++] = array[i];
            }
        }
        for (int i = 0; i < len; i++) {
            if ((array[i] & 1) == 0) {
                newA[cur++] = array[i];
            }
        }
        int c = 0;
        for (int a : newA) {
            array[c++] = a;
        }
    }
}
