package com.algorithms.tree;

import java.util.HashMap;

/**
 * 描述    :
 * Author :QingX
 * Date   :2019-07-31 17:01
 */
public class RedBlackTree<Key extends Comparable <Key>, Value> {

    public static void main(String[] args) {
        RedBlackTree <String, Object> redBlackTree = new RedBlackTree <>();
        redBlackTree.put("a", "a");
        redBlackTree.put("b", "b");
        redBlackTree.put("c", "c");
        System.out.println(redBlackTree.size());
        System.out.println(redBlackTree.get("d"));
    }

    /**
     * 根节点
     **/
    private Node root;

    /**
     * 描述    :私有内部节点类，用于表示二叉树各个节点
     * Author :Qing_X
     * Date   :2019-51-13 21:51:22
     */
    private class Node {
        private Node parent,left,right;
        private Key key;
        private Value value;
        private int N;
        private boolean isRed;

        public Node(Key k, Value v) {
            this.key = k;
            this.value = v;
            this.isRed = true;
            N = 1;
        }
    }

    /**
     * 描述    :获取红黑树的键值对 key为空时，返回空
     * Author :Qing_X
     * Date   :2019-38-03 10:38:10
     */
    public Value get(Key key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public Node getNode(Node node, Key key) {
        if (node != null) {
            if (node.key.equals(key))
                return node;
            else if (node.key.compareTo(key) > 0)
                return getNode(node.left, key);
            else
                return getNode(node.right, key);
        } else {
            return null;
        }
    }

    public void put(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value);
            root.isRed = false;
            return;
        }
        root = put(null, root, key, value);
        root.isRed = false;
    }

    public Node put(Node parent, Node node, Key key, Value value) {
        if (node == null) {
            node = new Node(key, value);
            node.parent = parent;
        } else {
            int cmp = node.key.compareTo(key);
            if (cmp < 0) {
                node.right = put(node, node.right, key, value);
                node.N=size(node.left)+size(node.right)+1;
            } else if (cmp > 0) {
                node.left = put(node, node.left, key, value);
                node.N=size(node.left)+size(node.right)+1;
            } else {
                node.value = value;
            }

        }
        //父亲节点为黑色，直接插入
        if (node.parent == null || !node.parent.isRed) {
            return node;
        }
        //当前节点和父节点都为红色
         if (node.isRed && node.parent.isRed) {
            //父节点为左节点且当前节点为右节点，将当前节点挪到左节点上
            if (node.parent.parent.left == node.parent && node.parent.parent.right == null) {
                if (node.parent.right == node) {
                    node.parent.left = node;
                    node.parent.right = null;
                }
                rightRotate(node.parent.parent);
            } else if (node.parent.parent.right == node.parent && node.parent.parent.left == null) {
                if (node.parent.left == node) {
                    node.parent.right = node;
                    node.parent.left = null;
                }
                leftRotate(node.parent.parent);
            } else if (node.parent.parent.left.isRed && node.parent.parent.right.isRed) {
                node.parent.parent.left.isRed = false;
                node.parent.parent.right.isRed = false;
                node.parent.parent.isRed = true;
            }
        }
        return node;
    }

    /**
     * 描述    :对节点进行左旋
     * Author :QingX
     * Date   :2019-26-31 17:26:38
     */
    private Node leftRotate(Node node) {
        Node r = node.right;
        boolean color = node.isRed;
        node.isRed = r.isRed;
        r.isRed = color;
        node.right = r.left;
        node.N = size(node.left) + size(node.right) + 1;
        r.left = node;
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    private Node rightRotate(Node node) {
        Node l = node.left;
        boolean isRed = node.isRed;
        node.isRed = l.isRed;
        l.isRed = isRed;
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
    private int size(Node node) {
        return node == null ? 0 : node.N;
    }

    /**
     * 描述    :返回node节点大小
     * Author :QingX
     * Date   :2019-35-31 17:35:24
     */
    private int size() {
        return size(root);
    }
}
