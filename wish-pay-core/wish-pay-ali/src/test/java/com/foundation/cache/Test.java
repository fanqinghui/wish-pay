package com.foundation.cache;


import java.util.Date;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/8 18:04
 */
public class Test {

    public static void main(String[] argus) {
        double m = 0;
        long t1 = new Date().getTime();
        for (double i = 2; i < 999999999; i++) {
            m += ((i + 1) / (i - 1) * (i - (i / i - i))) - ((i + 1) / (i - 1) * (i - (i / i - i)) - 1);
        }
        long t2=new Date().getTime();
        System.out.println(t2-t1);
    }
    //执行时间 10415
}



