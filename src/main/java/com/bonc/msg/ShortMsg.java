package com.bonc.msg;

import java.util.Stack;

/**
 * 描述    :短链服务生成
 * Author :Qing_X
 * Date   :2019-02-22 23:07
 */
public class ShortMsg {

    public static void main(String[] args) {
        System.out.println(getShortUrlByLongNum(0L));
    }

    //62进制
    public static int BASE_NUM = 62;

    //62进制字母顺序
    public static final char[] array = {'G', 'q', 'w', '0', 'H', 'e', 'T', 'F', '9', 'r', 'V', 't', 'y', 'u', 'N', 'i', '6', 'D', 'o', 'p', 'L', 'a', 's', 'd', 'K', 'f', 'g', 'h', 'j', 'k', '4', 'l', 'z', 'x', 'c', 'v', 'b', 'S', 'n', 'm', '1', 'Z', '3', '5', 'Q', 'W', 'E', 'R', '7', 'Y', 'U', 'I', 'O', '2', 'P', 'A', 'J', 'X', 'C', 'B', '8', 'M'};

    /**
     * 将10进制数转为62进制字符串(短网址)
     *
     * @param number
     * @return
     */
    public static String getShortUrlByLongNum(Long number) {
        Long rest = number;
        Stack <Character> stack = new Stack <>();
        StringBuilder result = new StringBuilder(0);
        if (0 == rest) {
            return String.valueOf(array[0]);
        }
        while (rest != 0) {
            stack.add(array[new Long((rest - (rest / BASE_NUM) * BASE_NUM)).intValue()]);
            rest = rest / BASE_NUM;
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    /**
     * 通过短网址返回10进制数
     *
     * @param shortUrl
     * @return
     */
    public static Long getLongNumByShortUrl(String shortUrl) {
        long multiple = 1;
        long result = 0;
        Character c;
        for (int i = 0; i < shortUrl.length(); i++) {
            c = shortUrl.charAt(shortUrl.length() - i - 1);
            result += valueOfCharacter(c) * multiple;
            multiple = multiple * BASE_NUM;
        }
        return result;
    }

    /**
     * 字母对应的值 如array数组 G对应0 q对应1
     *
     * @param c
     * @return
     */
    private static int valueOfCharacter(Character c) {
        for (int i = 0; i < array.length; i++) {
            if (c == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
