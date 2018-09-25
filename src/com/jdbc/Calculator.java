package com.jdbc;

import java.util.Stack;

public class Calculator {

    /**
     * 数字栈：存储表达式中的数字
     */
    private Stack<String> numStack = null;
    /**
     * 符号栈：存储表达式中的运算符和括号
     */
    private Stack<Character> charStack = null;

    /**
     * 计算四则运算表达式，返回计算结果
     *
     */
    public String calculate(String numStr) {
        numStr = removeStrSpace(numStr);
        
        if (numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "=";
        }
        if (!isStandard(numStr)) {
            return "0";
        }

        numStack = new Stack<String>();
        charStack = new Stack<Character>();
        StringBuffer temp = new StringBuffer();
        
        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i);
            if (isNumber(ch) || ch == '/') { 
                temp.append(ch);
            } else {
                String tempStr = temp.toString();
                if (!tempStr.isEmpty()) {
                    numStack.push(tempStr);
                    temp = new StringBuffer();
                }
                while (!comparePri(ch) && !charStack.empty()) {
                    String a = numStack.pop(); 
                    String b = numStack.pop();
                    Fraction f1 = null;
                    Fraction f2 = null;
                    if (a.contains("/")) {
                        String[] alist = a.split("/");
                        f1 = new Fraction(Integer.parseInt(alist[0]), Integer.parseInt(alist[1]));
                    } else {
                        f1 = new Fraction(Integer.parseInt(a), 1);
                    }
                    if (b.contains("/")) {
                        String[] blist = b.split("/");
                        f2 = new Fraction(Integer.parseInt(blist[0]), Integer.parseInt(blist[1]));
                    } else {
                        f2 = new Fraction(Integer.parseInt(b), 1);
                    }
                    switch (charStack.pop()) {
                        case '+':
                            numStack.push(f2.add(f1).print());
                            break;
                        case '-':
                        	if ((f1.x/f1.y) >= (f2.x/f2.y)) {
                                return null;
							
						}
                            numStack.push(f2.minus(f1).print());
                            break;
                        case 'x':
                            numStack.push(f2.multiply(f1).print());
                            break;
                        case '÷':
                            if (f1.x==0){
                                return null;
                            }
                            numStack.push(f2.divide(f1).print());
                            break;
                        default:
                            break;
                    }
                } 
                if (ch != '=') {
                    charStack.push(new Character(ch)); 
                    if (ch == ')') { 
                        charStack.pop();
                        charStack.pop();
                    }
                }
            }
        } 

        return numStack.pop(); 
    }


    private String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

 
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) 
            return false;
        Stack<Character> stack = new Stack<Character>(); 
        boolean b = false; 
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            if (!(isNumber(n) || "(".equals(n + "") || ")".equals(n + "")
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "x".equals(n + "") || "÷".equals(n + "") || "/".equals(n + "")
                    || "=".equals(n + ""))) {
                return false;
            }
            
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { 
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) 
                    return false;
            }
            
            if ("=".equals(n + "")) {
                if (b)
                    return false;
                b = true;
            }
        }
        if (!stack.isEmpty())
            return false;
        if (!("=".equals(numStr.charAt(numStr.length() - 1) + "")))
            return false;
        return true;
    }


    private boolean isNumber(char num) {
        if (num >= '0' && num <= '9')
            return true;
        return false;
    }

    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     */
    private boolean comparePri(char symbol) {
        if (charStack.empty()) { 
            return true;
        }


        char top = charStack.peek();
        if (top == '(') {
            return true;
        }
        switch (symbol) {
            case '(': 
                return true;
            case 'x': {
                if (top == '+' || top == '-') 
                    return true;
                else
                    return false;
            }
            case '÷': {
                if (top == '+' || top == '-') 
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': 
                return false;
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }

    String getFinalResult(String str) {
        if (!str.contains("/"))
            return str;
        String[] part = str.split("/");
        int a = Integer.parseInt(part[0]);
        int b = Integer.parseInt(part[1]);
        
        if (a == b)
            return "1";
        else if (a > b && a % b != 0) {
            return a / b + "’" + a % b + "/" + b;
        } else if (a < b && -a > b && (-a) % b != 0) {
            return "-" + (-a) / b + "’" + (-a) % b + "/" + b;
        } else if (b == 1)
            return a + "";
        else
            return a + "/" + b;
    }

}
