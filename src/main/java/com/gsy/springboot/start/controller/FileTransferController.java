package com.gsy.springboot.start.controller;

import com.gsy.springboot.start.service.FileTransferService;
import com.gsy.springboot.start.util.LogUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/5
 */
@Controller
@RequiresPermissions("ftp")
public class FileTransferController {
    @Autowired
    FileTransferService fileTransferService;

    @PostMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest httpServletRequest) {
        String result = fileTransferService.uploadFile(file);
        LogUtil.info(FileTransferController.class,"上传文件是否成功={},上传文件={},大小={}B，ip={}",result,file.getOriginalFilename(),file.getSize(),httpServletRequest.getRemoteHost());
        return result;
    }
    //这个暂时没有用了，予以保留
    @PostMapping(value="/uploadFiles")
    @ResponseBody
    public String multipleFilesUpload(HttpServletRequest request){
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        //遍历处理文件
        String info = "";
        for (MultipartFile file:files) {
            try {
                String s = fileTransferService.uploadFile(file);
                info = info+"-"+s;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }
    @GetMapping("upload")
    public String upload(){
        return "filetransfer/upload";
    }

    /**
     * 写法较为古老，不应该使用。废弃较为合理
     * @param fileRelativePath
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @GetMapping("/downloadFile/{fileRelativePath}")
    @ResponseBody
    public String DownloadFile(@PathVariable("fileRelativePath") String fileRelativePath, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        fileRelativePath = fileRelativePath.replaceAll("@path@","\\\\");
        String result = fileTransferService.downLoadFile(fileRelativePath, httpServletResponse);
        LogUtil.info(FileTransferController.class,"下载文件是否成功={},ip={},request={},请求文件相对路径={}", result, httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURI(),fileRelativePath);
        return result;
    }
    @GetMapping("/download")
    public String listFiles(ModelMap modelMap){
        List<Map> list = fileTransferService.listFiles();
        modelMap.addAttribute("fileList",list);
        return "filetransfer/listFiles";
    }
    @RequestMapping(value = "/downloadFile2/{fileRelativePath}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("fileRelativePath") String fileRelativePath,  HttpServletRequest httpServletRequest) {
        fileRelativePath = fileRelativePath.replaceAll("@path@","\\\\");
        ResponseEntity responseEntity = fileTransferService.downLoadFile2(fileRelativePath);
        LogUtil.info(FileTransferController.class,"下载文件是否成功={},ip={},request={},请求文件相对路径={}", responseEntity!=null?"成功":"失败", httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURI(),fileRelativePath);
        return responseEntity;
    }

}
