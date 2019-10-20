package com.sehwiii.demo.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author 161250078
 * 自定义的ArrayList和String的转化
 */
public class StringListTranf {

    public static String listToString(ArrayList<String> list) {
        String res = "";
        if (list == null) {
            res = "null";
        } else if (list.size() == 1) {
            res = list.get(0);
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                res = list.get(i) + "&";
            }
            res = res + list.get(list.size() - 1);
        }
        return res;
    }

    public static ArrayList<String> stringToList(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s.equals("null")) {
            return null;
        } else if (!s.contains("&")) {
            res.add(s);
        } else {
            String[] data = s.split("&");
            Collections.addAll(res, data);
        }
        return res;
    }
}
