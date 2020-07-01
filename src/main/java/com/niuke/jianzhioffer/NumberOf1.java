package com.niuke.jianzhioffer;

/**
 * 描述    :输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * Author :Qing_X
 * Date   :2019-08-13 13:43
 */
public class NumberOf1 {

    public static void main(String[] args) {
        new NumberOf1().NumberOf1(-1);
    }
    public int NumberOf1(int n) {
        int count=0;
        for (int i=0;i<32;i++){
            int m = (n&0x80000000>>>i)>>>31-i;
            if(m==1)
                count++;
        }
        return count;
    }
}
