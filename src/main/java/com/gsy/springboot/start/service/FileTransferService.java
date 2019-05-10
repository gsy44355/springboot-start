package com.gsy.springboot.start.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/5
 */
public interface FileTransferService {
    String uploadFile(MultipartFile file);
    String downLoadFile(String fileName, HttpServletResponse httpServletResponse);
    ResponseEntity<InputStreamResource> downLoadFile2(String fileName);
    List<Map> listFiles();
}
