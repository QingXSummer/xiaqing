package com.niuke.jianzhioffer;


import java.util.LinkedList;
import java.util.List;

/**
 * 描述    :输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * Author :QingX
 * Date   :2019-09-08 16:09:20
 */

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode node = solution.reConstructBinaryTree(pre, in);
        System.out.println(node.val);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return getNode(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode getNode(int[] pre, int startPre, int endPre,
                             int[] in, int startIn, int endIn) {
        TreeNode treeNode = null;
        if (startIn > endIn || startPre>endPre)
            return null;
        for (int i = startIn; i <= endIn; i++) {
            if (pre[startPre] == in[i]) {
                treeNode = new TreeNode(pre[startPre]);
                treeNode.left = getNode(pre, startPre +1, endPre, in, startIn, i - 1);
                treeNode.right = getNode(pre, startPre +1, endPre, in, i + 1, endIn);
                break;
            }
            if (i == endIn) {
                startPre++;
                if(startPre>endPre)
                    return null;
                i = startIn-1;
                continue;
            }
        }
        return treeNode;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}