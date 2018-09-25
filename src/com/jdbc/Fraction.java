package com.jdbc;

public class Fraction {
    int x;				//分子
    int y;				//分母
    private Fraction temp;

    public Fraction(int a, int b) {
        x = a;
        y = b;
    }

    Fraction plus(Fraction r) {
        temp = new Fraction(0, 0);
        temp.x = x * r.y + y * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Fraction minus(Fraction r) {
        temp = new Fraction(0, 0);
        temp.x = x * r.y - y * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Fraction multiply(Fraction r) {
        temp = new Fraction(0, 0);
        temp.x = x * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Fraction divide(Fraction r) {
        temp = new Fraction(0, 0);
        temp.x = x * r.y;
        temp.y = y * r.x;
        return temp;
    }

    String print() {
        /**
         * 计算结果化简
         */
        if (x == 0) {
            return "0";
        } else {
            int n;
            if (x > y)
                n = x;
            else
                n = y;
            int maxn = 0;
            for (int i = 1; i <= n; ++i) {   //约分
                if (x % i == 0 && y % i == 0)
                    maxn = i;
            }
            int a = x / maxn;
            int b = y / maxn;
            if (a == b)
                return "1";
            else if(b==1)
                return a+"";
            else
                return a + "/" + b;
        }
    }
}
