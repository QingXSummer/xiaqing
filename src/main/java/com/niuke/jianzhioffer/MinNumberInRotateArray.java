package com.niuke.jianzhioffer;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-08-12 14:38
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0)
            return 0;
        if (array.length==1)
            return array[0];
        for (int i=0;i<array.length;i++){
            if(array[i]>array[i+1]){
                return array[i+1];
            }
        }
        return 0;
    }
}
