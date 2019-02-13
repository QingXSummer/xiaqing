package com.algorithms.queue;


/**
 * Author :Qing_X
 * Date   :2019-01-22 21:00.
 */
public class MaxPQ<key extends Comparable <key>> {
    key[] keys;
    private int N = 0;

    public MaxPQ(key[] keys) {
        this.keys = keys;
    }

    /**
     * 功能描述
     *
     * @return int
     * @author Qing_X
     * @date 2019-01-26 19:10:25
     */
    public int size() {
        return N;
    }

    /**
     * 功能描述: 查询优先级队列是否为空
     *
     * @param
     * @return boolean
     * @author Qing_X
     * @date 2019-02-26 23:02:59
     */
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    /**
     *
     *功能描述: 添加元素，保留最小的初始化数组减1个。
     * @param k
     * @return key
     * @author Qing_X
     * @date 2019-09-28 23:09:21
     */
    public key add(key k) {
        if (N < keys.length - 1) {
            insert(k);
            return null;
        } else if (k.compareTo(keys[1]) < 0) {
            key delete =keys[1];
            keys[1]=k;
            sink(1);
            return delete;
        }
        return null;
    }

    /**
     * 功能描述: 插入元素
     *
     * @param
     * @return int
     * @author Qing_X
     * @date 2019-34-28 21:34:39
     */
    public int insert(key k) {
        keys[++N] = k;
        swim(N);
        return size();
    }

    /**
     * 功能描述: 删除最大的元素
     *
     * @param
     * @return key
     * @author Qing_X
     * @date 2019-41-28 22:41:52
     */
    public key deleteMax() {
        if (N < 1)
            return null;
        key max = keys[1];
        if (N == 1) {
            keys[1] = null;
        } else {
            change(1, N--);
            sink(1);
        }
        return max;
    }


    /**
     * 功能描述: 比较大小，上浮元素
     *
     * @param pos 指针指向当前位置
     * @return void
     * @author Qing_X
     * @date 2019-27-28 21:27:28
     */
    private void swim(int pos) {
        int p = pos / 2;
        if (p <= 0)
            return;
        if (!cmp(p, pos)) {
            change(p, pos);
            swim(p);
        }
    }

    /**
     * 功能描述: 下沉元素
     *
     * @param position 下沉的位置
     * @return void
     * @author Qing_X
     * @date 2019-10-27 13:10:41
     */
    private void sink(int position) {
        int k = position * 2;
        if (k > size())
            return;
        if (!cmp(position, k)) {
            change(position, k);
            sink(k);
        }
        k = position * 2 + 1;
        if (k > size())
            return;
        if (!cmp(position, k)) {
            change(position, k);
            sink(k);
        }

    }

    //交换数组中两个位置的元素
    private void change(int i, int j) {
        key k1 = keys[i];
        keys[i] = keys[j];
        keys[j] = k1;
    }

    //比较大小 i位置比大返回true
    private boolean cmp(int i, int j) {
        return keys[i].compareTo(keys[j]) >= 0 ? true : false;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= N; i++) {
            buffer.append(keys[i]);
            buffer.append(" ");
        }
        return buffer.toString();
    }
}

