package com.jdbc;

import java.util.Stack;

public class CreateTree
{

    /**
     * 后缀表达式转二叉表达式树
     */
    public static void tree(String suffixStr)
    {
        if (isEmpty(suffixStr))
        {
            return;
        }
        char[] chs = suffixStr.toCharArray();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        for (int i = 0; i < chs.length; i++)
        {
            if (isOperator(chs[i]))
            {
                if (stack.isEmpty() || stack.size() < 2)
                {
                    System.err.println("输入的后缀表达式不正确");
                    return;
                }
                TreeNode root = new TreeNode(chs[i]);
                root.left = stack.pop();
                root.right = stack.pop();
                stack.push(root);
            } else {
                stack.push(new TreeNode(chs[i]));
            }
        }
        if (stack.isEmpty() || stack.size() > 1)
        {
            System.err.println("输入的后缀表达式不正确");
            return;
        }
        stack.pop().printAll();
    }


    public static boolean isOperator(char c)
    {
        if (c == 'x' || c == '/' || c == '+' || c == '-')
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str)
    {
        if (str == null || "".equals(str))
        {
            return true;
        }
        return false;
    }

    private static class TreeNode
    {
        Object val;
        TreeNode left;
        TreeNode right;
        public TreeNode(Object val)
        {
            this.val = val;
        }
        
        public void print(int d)
        {
            StringBuilder printStrBuilder = new StringBuilder("");
            for (int i = 0; i < d; i++)
            {
                printStrBuilder.append("- ");
            }
            printStrBuilder.append(val);
            System.out.println(printStrBuilder);
            if (left != null)
            {
                left.print(d + 1);
            }
            if (right != null)
            {
                right.print(d + 1);
            }
        }

        public void printAll()
        {
            print(0);
        }
    }
}
