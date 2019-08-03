package com.algorithms.tree;

import java.util.HashMap;

/**
 * 描述    :
 * Author :QingX
 * Date   :2019-07-31 17:01
 */
public class RedBlackTree<key extends Comparable <key>, value> {
    /**
     * 描述    :私有内部节点类，用于表示二叉树各个节点
     * Author :Qing_X
     * Date   :2019-51-13 21:51:22
     */

    public static void main(String[] args) {
        HashMap map;
    }

    private class Node {
        private  Node parent;
        private key k;
        private value v;
        private Node left;
        private Node right;
        private int N;
        private boolean isRed ;

        public Node(key k, value v) {
            this.k = k;
            this.v = v;
            this.isRed=true;
            N = 1;
        }
    }

    /**
     * 描述    :对节点进行左旋
     * Author :QingX
     * Date   :2019-26-31 17:26:38
     */
    private Node leftRotate(Node node) {
        Node r = node.right;
        boolean color = node.isRed;
        node.isRed=r.isRed;
        r.isRed=color;
        node.right = r.left;
        node.N = size(node.left) + size(node.right) + 1;
        r.left = node;
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    private Node rightRotate(Node node) {
        Node l = node.left;
        boolean isRed = node.isRed;
        node.isRed=l.isRed;
        l.isRed=isRed;
        node.left = l.right;
        node.N = size(node.left) + size(node.right) + 1;
        l.right = node;
        l.N = size(l.left) + size(l.right) + 1;
        return l;
    }

    /**
     * 描述    :返回node节点大小
     * Author :QingX
     * Date   :2019-35-31 17:35:24
     */
    public int size(Node node) {
        return node == null ? 0 : node.N;
    }
}
