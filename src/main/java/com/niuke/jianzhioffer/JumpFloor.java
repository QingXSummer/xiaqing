package com.niuke.jianzhioffer;

/**
 * 描述    :一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * Author :Qing_X
 * Date   :2019-08-12 15:06
 */
public class JumpFloor {

    public static void main(String[] args) {
//        JumpFloor jumpFloor=new JumpFloor();
//        System.out.println(jumpFloor.JumpFloor(3));
        System.out.println(JumpFloorII(4));
    }

    private int count =0;

    public int JumpFloor(int target) {
        JumpFloor1(target,2);
        return count;
    }

    public int JumpFloor1(int target,int len) {
        for(int i=1;i<=len;i++){
            int t1 = target-i;
            if(t1>0){
                JumpFloor1(target-i,len);
            }else if (t1==0){
                count++;
            }
        }
        return 0;
    }


    public static int JumpFloorII(int target) {
        return target>1?1<<target-1:1;
    }
}
