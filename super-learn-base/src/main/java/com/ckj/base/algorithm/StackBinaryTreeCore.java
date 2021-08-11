package com.ckj.base.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * @author c.kj
 * @Description 二叉树遍历  栈实现
 * @Date 2021/7/21
 * @Time 9:29 AM
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class StackBinaryTreeCore {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer value;

        TreeNode(TreeNode left, TreeNode right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        TreeNode() {
        }


        public int getValue() {
            return value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }


        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", value=" + value +
                    '}';
        }
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode();
        treeNode1.value = 4;
        treeNode1.left = new TreeNode(new TreeNode(null, null, 1),
                new TreeNode(null, null, 3), 2);
        treeNode1.right = new TreeNode(new TreeNode(null, null, 0),
                new TreeNode(null, null, 6), 5);
        System.out.println(JSON.toJSONString(treeNode1));
        TreeNode h = treeNode1;
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(h);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && c.left != h && c.right != h) {
                    stack.push(c.left);
                } else if (c.right != null && c.right != h) {
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }

            }
        }
    }
}
