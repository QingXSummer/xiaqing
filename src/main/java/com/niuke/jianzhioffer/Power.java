package com.niuke.jianzhioffer;

/**
 * 描述    :给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * Author :Qing_X
 * Date   :2019-08-13 17:01
 */
public class Power {

    public static void main(String[] args) {
    }

    public double power(double base, int exponent) {
        if(exponent==0)
            return 1.0;
        boolean flag = exponent>0?true:false;
        double d=1l;
        if(!flag) exponent=-exponent;
        while (exponent!=0){
            d*=base;
            exponent--;
        }
        return flag?d:1/d;
    }
}
