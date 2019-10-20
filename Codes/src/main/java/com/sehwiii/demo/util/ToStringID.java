package com.sehwiii.demo.util;

/**
 * @author 161250078
 * 获得下一个id（任务或用户）
 */

public class ToStringID {
    public static String toStringID(int num) {
        String id = String.valueOf(num);
        for (int i = 0; i <= 7 - id.length(); i++) {
            id = "0" + id;
        }
        return id;
    }
}
