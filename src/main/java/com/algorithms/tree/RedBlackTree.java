package com.algorithms.tree;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述    :
 * Author :QingX
 * Date   :2019-07-31 17:01
 */
public class RedBlackTree<Key extends Comparable <Key>, Value> {

    public static void main(String[] args) {
        RedBlackTree <String, Object> redBlackTree = new RedBlackTree <>();
        redBlackTree.put("1", "1");
        redBlackTree.put("2", "2");
        redBlackTree.put("3", "3");
        redBlackTree.put("4", "4");
        redBlackTree.put("5", "5");
        redBlackTree.put("6", "6");
        redBlackTree.put("7", "7");
        redBlackTree.put("8", "8");
        redBlackTree.delete("3");
        redBlackTree.delete("4");
        redBlackTree.delete("5");
        redBlackTree.delete("6");
        redBlackTree.delete("7");
        redBlackTree.delete("8");
        redBlackTree.delete("2");
        System.out.println(redBlackTree.size());
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
        private Node parent, left, right;
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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", N=" + N +
                    ", isRed=" + isRed +
                    '}';
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
        put(root, key, value);
        root.isRed = false;
    }

    public Node put(Node node, Key key, Value value) {
        Node l, t = node;
        boolean isLeft = false;
        boolean sizeInc = get(key) == null ? false : true;
        while (true) {
            int cmp = key.compareTo(node.key);
            l = t;
            if (cmp == 0) {
                t.value = value;
                return node;
            } else if (cmp > 0) {
                if (!sizeInc)
                    t.N = t.N + 1;
                t = t.right;
                isLeft = false;
            } else {
                if (!sizeInc)
                    t.N = t.N + 1;
                t = t.left;
                isLeft = true;
            }
            if (t == null)
                break;
        }
        Node newN = new Node(key, value);
        newN.parent = l;
        if (isLeft)
            l.left = newN;
        else
            l.right = newN;
        //父亲节点为黑色或为根节点，直接插入
        Node p = newN.parent;
        if (p == null || !isRed(p)) {
            return node;
        }
        fixBalance(newN);
        return node;
    }

    //修复红黑树平衡关系
    private void fixBalance(Node node) {
        Node pa, unc;
        while (isRed(pa = node.parent)) {
            Node gp = pa.parent;
            unc = getUncle(node);
            if (isRed(unc)) {
                pa.isRed = false;
                unc.isRed = false;
                gp.isRed = true;
                node = gp;
                continue;
            }
            if (isLeft(pa)) {
                if (!isLeft(node))
                    gp.left = leftRotate(pa);
                rightRotate(gp);
            } else {
                if (isLeft(node))
                    rightRotate(pa);
                leftRotate(gp);
            }
        }
        this.root.isRed = false;
    }


    /**
     * 描述    :对节点进行左旋
     * Author :QingX
     * Date   :2019-26-31 17:26:38
     */
    private Node leftRotate(Node node) {
        Node p = node.parent;
        Node r = node.right;
        r.parent = p;
        boolean color = node.isRed;
        node.isRed = r.isRed;
        r.isRed = color;
        if (r.left != null) {
            r.left.parent = node;
        }
        node.right = r.left;
        node.N = size(node.left) + size(node.right) + 1;
        if (p == null) {
            root = r;
        } else if (isLeft(node)) {
            p.left = r;
        } else {
            p.right = r;
        }
        node.parent = r;
        r.left = node;
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    private Node rightRotate(Node node) {
        Node p = node.parent;
        Node l = node.left;
        l.parent = p;
        boolean isRed = node.isRed;
        node.isRed = l.isRed;
        l.isRed = isRed;
        if (l.right != null) {
            l.right.parent = node;
        }
        node.left = l.right;
        node.N = size(node.left) + size(node.right) + 1;
        if (p == null) {
            root = p;
        } else if (isLeft(node)) {
            p.left = l;
        } else {
            p.right = l;
        }
        node.parent = l;
        l.right = node;
        l.N = size(l.left) + size(l.right) + 1;
        return l;
    }

    /**
     * 描述    :删除键值对应的key
     * Author :QingX
     * Date   :2019-04-05 17:04:34
     */
    public Value delete(Key key) {
        Node node = deleteNode(root, key);
        return node == null ? null : node.value;
    }

    private Node deleteNode(Node node, Key key) {
        Node d = node;
        while (true) {
            int cmp = key.compareTo(d.key);
            if (cmp == 0) {
                break;
            } else if (cmp > 0) {
                d = d.right;
            } else {
                d = d.left;
            }
            if (d == null) {
                return null;
            }
        }
        return deleteNode(d);
    }

    private Node deleteNode(Node d) {
        Node p = d.parent;
        //叶子节点
        if (d.left == null && d.right == null) {
            if (p == null) {
                this.root = null;
                return d;
            }
            if (isRed(d)) {
                if (isLeft(d))
                    p.left = null;
                else
                    p.right = null;
            } else
                deleteBlackNode(d, true);
            fixSize(p);
        } else if (d.left == null) { // 有右节点
            if (p != null) {
                if (isLeft(d)) {
                    p.left = d.right;
                } else {
                    p.right = d.right;
                }
            }
            d.right.isRed = d.isRed;
            d.right.parent = p;
            fixSize(d.right);
        } else if (d.right == null) { //有左节点
            if (p != null) {
                if (isLeft(d)) {
                    p.left = d.left;
                } else {
                    p.right = d.left;
                }
            }
            d.left.isRed = d.isRed;
            d.left.parent = p;
            fixSize(d.left);
        } else { //同时拥有两个节点
            Node t = d.right;
            Node l = t;
            while (t != null) {
                l = t;
                t = t.left;
            }
            d.value = l.value;
            d.key = l.key;
            deleteNode(l);
        }

        return null;
    }


    private Node deleteBlackNode(Node node, boolean delete) {
        Node p = node.parent;
        Node sib = getSibling(node);
        if (p != null) {//P为空说明是根节点
            if (isRed(p)) {
                if (sib.left == null && sib.right == null) {
                    p.isRed = false;
                    sib.isRed = true;
                } else if (sib.left == null) {
                    if (isLeft(node)) {
                        leftRotate(p);
                        p.parent.right.isRed = false;
                    } else {
                        leftRotate(sib);
                        rightRotate(p);
                        p.parent.left.isRed = false;
                    }
                } else if (sib.right == null) {
                    if (isLeft(node)) {
                        rightRotate(sib);
                        leftRotate(p);
                        p.parent.right.isRed = false;
                    } else {
                        rightRotate(p);
                        p.parent.left.isRed = false;
                    }
                }
            } else {
                if (isRed(sib)) {
                    if (isLeft(node))
                        leftRotate(p);
                    else
                        rightRotate(p);
                } else {
                    if (sib.left == null && sib.right == null) {
                        sib.isRed = true;
                        deleteBlackNode(p, false);
                    } else if (sib.right != null) {
                        if (isLeft(node)) {
                            leftRotate(p);
                            sib.right.isRed = false;
                        } else {
                            leftRotate(sib);
                            rightRotate(p);
                            sib.left.isRed = false;
                        }
                    } else if (sib.left != null) {
                        if (isLeft(node)) {
                            rightRotate(sib);
                            leftRotate(p);
                            sib.isRed = false;
                        } else {
                            rightRotate(p);
                            sib.left.isRed = false;
                        }
                    }

                }
            }
        }
        if (delete) {
            if (p == null)
                this.root = null;
            else if (isLeft(node))
                p.left = null;
            else
                p.right = null;
            node.parent = null;
        }
        fixSize(p);
        return node;
    }

    private void fixSize(Node node) {
        if(node==null)
            return;
        Node p = node;
        Node l;
        do {
            l = p;
            p.N = size(p.left) + size(p.right) + 1;
            p = p.parent;

        } while (p != null);
        this.root = l;
    }

    //判断节点是否为空色节点
    private boolean isRed(Node node) {
        return node == null ? false : node.isRed;
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
    public int size() {
        return size(root);
    }


    /**
     * 描述    : 判断节点是否为左子节点
     * Author :QingX
     * Date   :2019-01-05 09:01:13
     */
    private boolean isLeft(Node node) {
        if (node.parent == null)
            return false;
        return node.parent.left == node ? true : false;
    }

    /**
     * 描述    : 判断节点是否为右子节点
     * Author :QingX
     * Date   :2019-02-05 09:02:40
     */
    private boolean isRight(Node node) {
        if (node == null || node.parent == null)
            return false;
        return node.parent.right == node ? true : false;
    }

    /**
     * 描述    :获取叔叔节点 不存在返回null
     * Author :QingX
     * Date   :2019-03-05 10:03:11
     */
    private Node getUncle(Node node) {
        Node pa = node.parent;
        if (pa != null) {
            Node ppa = pa.parent;
            if (ppa != null) {
                return ppa.left == pa ? ppa.right : ppa.left;
            }
        }
        return null;
    }

    /**
     * 描述    :获取兄弟节点
     * Author :QingX
     * Date   :2019-40-06 09:40:18
     */
    private Node getSibling(Node node) {
        Node pa = node.parent;
        if (pa == null) return null;
        return isLeft(node) ? pa.right : pa.left;
    }

}
