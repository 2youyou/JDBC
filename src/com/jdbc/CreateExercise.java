package com.jdbc;

import java.util.Random;


public class CreateExercise {
    String[] sign = {"+", "-", "x", "÷", "/"};
    int range_num;
    Random random = new Random();

    
    public void setMax_num(int range_num) {
        this.range_num =range_num;
    }

    
    public String create() {
        String str = "";
        int local = random.nextInt(3);
        
        for (int j = 0; j < 3; j++) {
            if (local == 0 && j == 0) {
                str += "(";
            } else if (local == 2 && j == 1) {
                str += "(";
            }
            
            str += random.nextInt(range_num) % range_num + 1;   //产生指定范围随机数
            if (local == 0 && j == 1) {
                str += ")";
            }
            if (local == 2 && j == 2) {
                str += ")";
            }
            
            String signElement = sign[random.nextInt(5)];//产生随机运算符号
            str += signElement;
            if (signElement == "/") {
                str += random.nextInt(range_num) % range_num + 1;
                signElement = sign[random.nextInt(5)];
                while (true) {
                    if (signElement != "/") {
                        str += signElement;
                        break;
                    }
                    signElement = sign[random.nextInt(5)];
                }
            }

        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public void belongFraction(String strfraction) {
        /**
         * 处理分数计算
         */
        String[] fractionlist = null;
        if (strfraction.contains("+")) {
            fractionlist = strfraction.split("\\+");
            CalculateFraction(fractionlist, 0);
        } else if (strfraction.contains("-")) {
            fractionlist = strfraction.split("-");
            CalculateFraction(fractionlist, 1);
        } else if (strfraction.contains("x")) {
            fractionlist = strfraction.split("\\x");
            CalculateFraction(fractionlist, 2);
        } else if (strfraction.contains("÷")) {
            fractionlist = strfraction.split("÷");
            CalculateFraction(fractionlist, 3);
        }

    }

    public void CalculateFraction(String[] strlist, int flag) {
        /**
         * 分数的四种基本运算
         */
        String[] fraction1 = new String[2];
        String[] fraction2 = new String[2];
        
        if (strlist[0].contains("/"))
            fraction1 = strlist[0].split("/");
        else {
            fraction1[0] = strlist[0];
            fraction1[1] = "1";
        }
        if (strlist[1].contains("/"))
            fraction2 = strlist[1].split("/");
        else {
            fraction2[0] = strlist[1];
            fraction2[1] = "1";
        }

        Fraction fr1 = new Fraction(Integer.parseInt(fraction1[0]), Integer.parseInt(fraction1[1]));
        Fraction fr2 = new Fraction(Integer.parseInt(fraction2[0]), Integer.parseInt(fraction2[1]));
        fr1.print();
        
        switch (flag) {
            case 0:
                fr1.add(fr2).print();
                break;
            case 1:
                fr1.minus(fr2).print();
                break;
            case 2:
                fr1.multiply(fr2).print();
                break;
            case 3:
                fr1.divide(fr2).print();
                break;
        }
    }
}
