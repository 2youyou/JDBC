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
        int range_num = scan.nextInt();
        System.out.print("是否开始答题（yes/no）：");
            String is_doNow=scan.next();

        LinkedHashMap<Integer, String> rightAnswerMap = new LinkedHashMap<Integer, String>();
        LinkedHashMap<Integer,String> exerciseMap = new LinkedHashMap<Integer, String>();
        ArrayList<Integer> rightRecord = new ArrayList<Integer>();
        ArrayList<Integer> wrongRecord = new ArrayList<Integer>();
        CreateExercise ce = new CreateExercise();
        IO save = new IO();
        ce.setRange_num(range_num);
        
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
                System.out.println(i + ":  " + problem + "=" );
                rightAnswerMap.put(i, result);
                if (!save.WriteToFile(i + ":  " + problem + "=", 0)) {
                    System.out.println("生成Exercise文件失败！");
                    System.exit(0);
                }
                if (!save.WriteToFile(+ i + ":  " + problem + "=" + result, 1)) {
                    System.out.println("生成Answer文件失败！");
                    System.exit(0);
                }
            } else {
                i--;
            }

            

        }

        if(is_doNow.equals("yes")){
            System.out.println("请输入答案(带分数用“ ’ ”分隔，如1’1/2)：");
            
            for (int i = 1; i <= problems_num; i++) {
                System.out.print( + i + ":  " + exerciseMap.get(i) + "=");
                String input = scan.next();
                if (rightAnswerMap.get(i).equals(input))
                    rightRecord.add(i);
                else
                    wrongRecord.add(i);
            }
            System.out.println("结果为：");

            if (rightRecord.size()!=0){
                System.out.print("正确题目:"+rightRecord.size()+" (");
                for (int i=0;i<rightRecord.size()-1;i++)
                    System.out.print(rightRecord.get(i)+",");
                System.out.println(rightRecord.get(rightRecord.size()-1)+")");
            }
            else
                System.out.println("正确题目:"+rightRecord.size());

            if (wrongRecord.size()!=0){
                System.out.print("错误题目:"+wrongRecord.size()+" (");
                for (int i=0;i<wrongRecord.size()-1;i++)
                    System.out.print(wrongRecord.get(i)+",");
                System.out.println(wrongRecord.get(wrongRecord.size()-1)+")");
            }
            else
                System.out.println("错误题目:"+wrongRecord.size());
        }
        if (save.CloseOutBufferedWriter())
            System.out.println("题目和答案文本创建成功");
        else
            System.out.println("题目和答案文本创建失败");
    }
}
