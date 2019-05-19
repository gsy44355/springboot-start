package com.gsy.springboot.start.util.crawler;

import com.gsy.springboot.start.util.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Created By Gsy on 2019/5/10
 */
public class WebCrawlerUtil {
    public static boolean getWebPicture(String urlStr,String pictureName, Map<String,String> headers,String directory)throws IOException{
        InputStream inStream = null;
        FileOutputStream outStream = null;
        try{
            //new一个URL对象
            URL url = new URL(urlStr);
            System.out.println(urlStr);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String,String> entry: entrySet) {
                conn.addRequestProperty(entry.getKey(),entry.getValue());
            }
            //通过输入流获取图片数据
            inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            if(data ==null || data.length == 0){
                System.out.println(WebCrawlerUtil.class+"图片解析失败，数据为空，url={}"+urlStr);
                return false;
            }
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(directory,pictureName);
            //创建输出流
            outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            return true;
        }catch (IOException e){
            System.out.println(WebCrawlerUtil.class+"下载图片失败");
            e.printStackTrace();
            throw e;
        }finally {
            FileUtils.safeClose(inStream);
            FileUtils.safeClose(outStream);
        }
    }
    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = null;
        try{
            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[4096];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while( (len=inStream.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //把outStream里的数据写入内存
            return outStream.toByteArray();
        }catch (IOException e){
            throw e;
        }finally {
            FileUtils.safeClose(inStream);
            FileUtils.safeClose(outStream);
        }
    }
    public static String get302Location(String url,Map<String,String> headers) throws IOException {
        String location = "";
        try {
            System.out.println("访问地址:" + url);
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String,String> entry: entrySet) {
                conn.addRequestProperty(entry.getKey(),entry.getValue());
            }
            conn.connect();
            location = conn.getHeaderField("Location");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
//            return get302Location (url,headers);
        }
        return location;

    }

    /**
     * 使用这个的时候，headers对于返回值的格式要求应该是accept-encoding=gzip, deflate, br
     * @param urlStr
     * @param headers
     * @param encoding
     * @return
     */
    public static String getWebHtml(String urlStr,Map<String,String> headers,String encoding) throws IOException {
        InputStream inputStream = null;
        String string = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            for (Map.Entry<String,String> entry: headers.entrySet()) {
                connection.addRequestProperty(entry.getKey(),entry.getValue());
            }
            inputStream = connection.getInputStream();
            GZIPInputStream gzis=new GZIPInputStream(inputStream);
            InputStreamReader reader = new InputStreamReader(gzis,encoding);
            br = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            string = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally {
            FileUtils.safeClose(inputStream);
            FileUtils.safeClose(br);
        }
        return string;
    }




}
