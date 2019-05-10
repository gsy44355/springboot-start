package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.service.FileTransferService;
import com.gsy.springboot.start.util.DirectoryUtils;
import com.gsy.springboot.start.util.FileUtils;
import com.gsy.springboot.start.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/5
 */
@Service
public class FileTransferServiceImpl implements FileTransferService {
    @Value("${gsy.default.file.download.dir}")
    String downloadPath;
    @Value("${gsy.default.file.upload.dir}")
    String uploadPath;

    @Override
    public String uploadFile(MultipartFile file) {
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        //加个时间戳，尽量避免文件名称重复
        String path = uploadPath +fileName;
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
        } catch (IOException e) {
            return "上传失败";
        }
        return file.getOriginalFilename()+"上传成功";
    }

    @Override
    @Deprecated
    public String downLoadFile(String fileName, HttpServletResponse httpServletResponse) {
        File file = new File(downloadPath+fileName);
        if(file.exists()){
            if(file.isDirectory()){
                return "文件夹无法下载";
            }else if(file.isFile()){
//                return file;
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream = null;
                ServletOutputStream outputStream = null;
                try {
//                    httpServletResponse.setCharacterEncoding("UTF-8");
//                    httpServletResponse.setContentType("application/force-download;charset=utf-8");// 设置强制下载不打开
                    httpServletResponse.setContentType("application/octet-stream;charset=utf-8");// 设置强制下载不打开
                    httpServletResponse.setHeader("Content-Disposition","attachment;fileName=" +new String(file.getName().getBytes(),"iso-8859-1"));
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    outputStream  = httpServletResponse.getOutputStream();
                    byte buffer[] = new byte[1024];
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1){
                        outputStream.write(buffer);
                        i = bufferedInputStream.read( buffer);
                    }
                    return "下载"+fileName+"成功";


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    FileUtils.safeClose(bufferedInputStream);
                    FileUtils.safeClose(fileInputStream);
                    FileUtils.safeClose(outputStream);
                }
            }
        }
        return "找不到文件";
    }

    @Override
    public ResponseEntity<InputStreamResource> downLoadFile2(String fileName) {
        try {
        String filePath = downloadPath+fileName;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"",new String(file.getFilename().getBytes(),"iso-8859-1")));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            LogUtil.error(this.getClass(),"error={}",e.getStackTrace());
            return null;
        }

    }

    @Override
    public List<Map> listFiles() {
        return DirectoryUtils.listAllFilesSize(new File(downloadPath));
    }
}
