package com.gsy.springboot.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * java调用cmd指令工具类
 *
 * @author le.li
 *
 */
class ExecuteUtil {
    /**
     * 避免乱码,如果没有传入语言编号，默认使用英文437<br>
     * D:\>chcp /? 显示或设置活动代码页编号。<br>
     * CHCP [nnn]<br>
     * nnn 指定代码页编号。<br>
     * 不带参数键入 CHCP 以显示活动代码页编号。<br>
     */
    private static final String DEFAULT_LANGUAGE_CODE = "437";

    /**
     * window系统默认语言:GBK
     */
    private static final String DEFAULT_LANGUAGE = "GBK";

    public static void main(String[] args) {
        // executeLink();

        // executeCmd("dir .");

        // 举例直接把bat文件当cmd指令调用
//        String cmd = null;
//        String fileName = "cmd";
//        File f = new File(".");
//        try {
//            cmd = f.getCanonicalPath() + File.separator + fileName;
//        } catch (IOException e) {
//            // e.printStackTrace();
//            System.err.println("get cmd file error.");
//        }
        executeCmd("");
    }

    /**
     * 获取操作系统默认语言
     *
     * @return String
     *
     *  java虚拟机启动默认的编码(一般和java文件设置格式一致)<br>
     *      System.out.println(Charset.defaultCharset());<br>
     *      查看预置的变量信息:System.getProperties().list(System.out);<br>
     *      属性:<br>
     *      文件编码:file.encoding<br>
     *      系统默认编码sun.jnu.encoding
     */
    private static String getsystemLanguage() {
        return null == System.getProperty("sun.jnu.encoding") ? DEFAULT_LANGUAGE
                : System.getProperty("sun.jnu.encoding");
    }

    /**
     * 执行cmd指令
     * @param cmd 执行指令
     */
    public static void executeCmd(String cmd) {
        executeLink(DEFAULT_LANGUAGE_CODE, true, cmd);
    }

    /**
     * cmd手工输入交互处理窗口
     */
    public static void executeLink() {
        executeLink(DEFAULT_LANGUAGE_CODE, false, "");
    }

    /**
     * cmd交互处理窗口
     *
     * @param languageCode 系统语言编码
     * @param isOneRun 只执行cmd指令
     * @param cmd 执行的指令
     *  在中文windows系统中，根据编码需要设置编码 chcp 65001 就是换成UTF-8代码页<br>
     *      chcp 936 可以换回默认的GBK<br>
     *      chcp 437 是美国英语 <br>
     */
    public static void executeLink(String languageCode, boolean isOneRun, String cmd) {
        try {
            String cmdBin = "cmd";
            if (isOneRun) {
                cmdBin = "cmd /c ";
            }
            Process process = Runtime.getRuntime().exec(cmdBin + cmd);
            PrintWriter writer = new PrintWriter(process.getOutputStream());
            if (!isOneRun) {
                // 此处可以预置交互指令
                // writer.println("chcp " + languageCode);
                writer.println("echo Hello World.");
                writer.flush();
            }
            CommandThread commandThread = new CommandThread(writer);
            commandThread.setName("ExecuteCmdThread");
            commandThread.start();
            ProcessInputStreamThread inputThread = new ProcessInputStreamThread(process.getInputStream());
            ProcessInputStreamThread errorThread = new ProcessInputStreamThread(process.getErrorStream());
            inputThread.setName("InputStreamThread");
            inputThread.start();
            errorThread.setName("ErrorStreamThread");
            errorThread.start();
            // 即使添加下边的一句也不会使线程结束
            // Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CommandThread extends Thread {
        PrintWriter writer;
        BufferedReader br = null;

        CommandThread(PrintWriter writer) {
            this.writer = writer;
            // 避免出现乱码问题,直接使用系统默认的编码格式
            br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(getsystemLanguage())));
            this.setDaemon(true);
        }

        @Override
        public void run() {
            try {
                String cmd = null;
                while ((cmd = br.readLine()) != null) {
                    writer.println(cmd);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != br) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ProcessInputStreamThread extends Thread {

        InputStream input;
        BufferedReader breader = null;

        ProcessInputStreamThread(InputStream input) {
            this.input = input;
            // 避免出现乱码问题,直接使用系统默认的编码格式
            breader = new BufferedReader(new InputStreamReader(input, Charset.forName(getsystemLanguage())));
        }

        @Override
        public void run() {
            try {
                String str = null;
                while ((str = breader.readLine()) != null) {
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != input) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (null != breader) {
                    try {
                        breader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}