package com.algorithms.tree;

/**
 * 二叉查找树 任意节点的值一定是大于左子节点，小于右子节点
 * Author :Qing_X
 * Date   :2019-02-13 21:28
 */
public class BST<key extends Comparable <key>, value> {

    private Node root;

    private Node deleteNode;

    /**
     * 描述    :私有内部节点类，用于表示二叉树各个节点
     * Author :Qing_X
     * Date   :2019-51-13 21:51:22
     */
    private class Node {
        private key k;
        private value v;
        private Node left;
        private Node right;
        private int N;

        public Node(key k, value v) {
            this.k = k;
            this.v = v;
            N = 1;
        }
    }

    /**
     * 功能描述: 根据指定的key值删除
     *
     * @param key
     * @return void
     * @author Qing_X
     * @date 2019-04-13 22:04:18
     */
    public void delete(key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, key k) {
        if (node == null) return null;
        int cmp = node.k.compareTo(k);
        if (cmp > 0) {
            node.left = delete(node.left, k);
        } else if (cmp < 0) {
            node.right = delete(node.right, k);
        } else {
            Node left = node.left;
            Node right = deleteMin(node.right);
            node = deleteNode;
            if (node == null) {
                return null;
            }
            node.left = left;
            node.right = right;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 功能描述: 返回当前树的节点值
     *
     * @param
     * @return int
     * @author Qing_X
     * @date 2019-04-13 22:04:18
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node != null ? node.N : 0;
    }

    /**
     * 功能描述: 存入key value ，构建二叉树
     *
     * @param k
     * @param v
     * @return void
     * @author Qing_X
     * @date 2019-04-13 22:04:18
     */
    public void put(key k, value v) {
        root = put(root, k, v);
    }

    private Node put(Node node, key k, value v) {
        if (node == null) return new Node(k, v);
        int cmp = node.k.compareTo(k);
        if (cmp > 0) node.left = put(node.left, k, v);
        else if (cmp < 0) node.right = put(node.right, k, v);
        else node.v = v;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /***
     *
     *功能描述: 通过key查找出对应的值，不存在则返回空
     * @param key
     * @return void
     * @author Qing_X
     * @date 2019-34-13 21:34:11
     */
    public value get(key key) {
        return get(root, key);
    }

    //二分法递归查找出指定的key对应的值
    private value get(Node node, key key) {
        if (node == null) return null;
        int comp = node.k.compareTo(key);
        if (comp > 0) return get(node.left, key);
        if (comp < 0) return get(node.right, key);
        return node.v;
    }

    public value deleteMin() {
        root = deleteMin(root);
        return value(deleteNode);
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) {
            deleteNode = node;
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public value value(Node node) {
        return node == null ? null : node.v;
    }




    public static void main(String[] args) {
        BST <Integer, Integer> bst = new BST <>();
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(3, 3);
//        bst.put(4, 4);
//        bst.put(5, 5);
        bst.delete(1);
//        bst.delete(2);
        System.out.println(bst.size());
    }

}

