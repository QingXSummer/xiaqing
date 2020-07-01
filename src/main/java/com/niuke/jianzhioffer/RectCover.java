package com.niuke.jianzhioffer;

/**
 * 描述    :我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * Author :Qing_X
 * Date   :2019-08-13 13:16
 * 解决    :数学归纳法，算出2*1 2*2 2*3 2*4 2*5
 */
public class RectCover {
    public static void main(String[] args) {
        System.out.println();
    }
    public  int RectCover(int target) {
        if(target==1) return 1;
        else if (target == 2) return 2;
        else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }
}
