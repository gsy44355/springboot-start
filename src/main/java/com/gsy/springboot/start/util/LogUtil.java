package com.gsy.springboot.start.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By Gsy on 2019/5/6
 */
public class LogUtil {
//    private static ConcurrentHashMap<Class,Logger> loggerMap = new ConcurrentHashMap<>();

    public static Logger getLogger(Class c){
//        Logger logger = loggerMap.get(c);
//        if(logger == null){
//            logger = LoggerFactory.getLogger(c);
//            loggerMap.put(c,logger);
////            System.out.println("创建logger");
//        }
        return LoggerFactory.getLogger(c);
    }
    public static void info(Class c,String format,Object ... objects){
        getLogger(c).info(format,objects);
    }
    public static void warn(Class c,String format,Object ... objects){
        getLogger(c).warn(format,objects);
    }
    public static void error(Class c,String format,Object ... objects){
        getLogger(c).error(format,objects);
    } public static void error(Class c,String message,Throwable throwable){
        getLogger(c).error(message,throwable);
    }
    public static void debug(Class c,String format,Object ... objects){
        getLogger(c).debug(format,objects);
    }

}
