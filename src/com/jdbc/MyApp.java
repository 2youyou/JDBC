package com.jdbc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MyApp {
	 public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        System.out.print("请输入希望生成的题目数量：");
	        int problems_num = scan.nextInt();
	        System.out.print("请输入题目的数值范围n(范围为0到n)：");
	        int max_num = scan.nextInt();

	        LinkedHashMap<Integer, String> rightAnswerMap = new LinkedHashMap<Integer, String>();
	        LinkedHashMap<Integer,String> exerciseMap=new LinkedHashMap<Integer, String>();
	        ArrayList<Integer> rightRecord=new ArrayList<Integer>();
	        ArrayList<Integer> wrongRecord=new ArrayList<Integer>();
	        CreateExercise ce = new CreateExercise();
	        ce.setMax_num(max_num);
	        
	        for (int i = 1; i <= problems_num; i++) {
	            String problem = ce.create();
	            exerciseMap.put(i,problem);
	            String ns = problem;
	            int rightbrackets;
	            int leftbrackets;
	            
	            if (problem.contains(")")) {
	                rightbrackets = problem.indexOf(")");
	                leftbrackets = problem.indexOf("(");
	                if (rightbrackets != problem.length() - 1 && problem.charAt(rightbrackets + 1) == '/') {
	                    StringBuilder sb = new StringBuilder(problem);
	                    if (leftbrackets - 1 > 0 && problem.charAt(leftbrackets - 1) == '÷')
	                        sb.replace(rightbrackets + 1, rightbrackets + 2, "x");
	                    else
	                        sb.replace(rightbrackets + 1, rightbrackets + 2, "÷");
	                    ns = sb.toString();
	                }
	            }


	            Calculator cal = new Calculator();
	            String result = cal.calculate(ns);
	            if (result != null) {
	                result = cal.getFinalResult(result);
	                System.out.println(i + ":  " + problem + "=" + result);
	                rightAnswerMap.put(i, result);
	            } 
	                else {
	                i--;
	            }           
	        }

	    }
}
