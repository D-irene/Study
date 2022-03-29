package com.lianxi.demo01.utils;

import java.util.Random;

public class OrderIdUtil {

    public static synchronized String createOrderId(){
        Random random = new Random();
        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(a);
    }
}
