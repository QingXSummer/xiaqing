package com.niuke.jianzhioffer;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-08-12 14:45
 */
public class Fibonacci {
    //递归
    public int Fibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci1(int n) {
        int result = 0;
        int pre=1;
        int prepre=0;
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            for (int i=2;i<=n;i++){
                result=pre+prepre;
                prepre=pre;
                pre=result;
            }
        }
        return result;
    }
}
