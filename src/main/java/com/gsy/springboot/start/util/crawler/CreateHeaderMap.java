package com.gsy.springboot.start.util.crawler;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created By Gsy on 2019/5/16
 */

public class CreateHeaderMap {
    /**
     * 获取headers的map 通过ResourceBundle
     * @param name 相对于resources目录下properties文件的名字，不加后缀
     * @return
     */
    public static Map<String,String> getMapByName(String name){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(name);
        Map<String ,String> map = new HashMap<>();
        Set<String> set = resourceBundle.keySet();
        for (String  s: set) {
            map.put(s,resourceBundle.getString(s));
        }
        return map;
    }

    /**
     * 获取headers的map ,附加一个属性值x-forwarded-for，参数为随机IP，通过ResourceBundle
     * @param name 相对于resources目录下properties文件的名字，不加后缀
     * @return
     */
    public static Map<String,String> getMapByNameWithRandomIp(String name){
        Map<String,String> map = getMapByName(name);
        map.put("x-forwarded-for",getRandomIp());
        return map;
    }

    /**
     * 获取随机IP
     * @return
     */
    public static String getRandomIp() {
        // ip范围
        int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
                { 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
                { 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
                { 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
                { 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
                { -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
                { -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
                { -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
                { -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
                { -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
        };
        Random rdint = new SecureRandom();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new SecureRandom().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /*
     * 将十进制转换成IP地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";
        b[0] = (ip >> 24) & 0xff;
        b[1] = (ip >> 16) & 0xff;
        b[2] = (ip >> 8) & 0xff;
        b[3] = ip & 0xff;
        x = b[0] + "." + b[1] + "." + b[2] + "." + b[3];
        return x;
    }
}
