package com.gsy.springboot.start.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryUtils {
    /**
     * 遍历文件
     *
     * @param file
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static List listAllFiles(File file, String typeString, String extension) {
        ArrayList list = new ArrayList();
        if (file.exists()) {
            if (file.isFile()) {
                String regex = "(?i).+?[.]" + extension + "$";
                if (extension.equals("") || file.getName().matches(regex)) {
                    switch (typeString) {
                        case "name":
                            list.add(file.getName());
                            break;
                        case "file":
                            list.add(file);
                            break;
                        default:
                            break;
                    }
                }
            } else if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    list.addAll(listAllFiles(file2, typeString, extension));
                }
            }
        }
        return list;
    }

    /**
     * 返回文件夹下所有文件名，不包括文件夹
     *
     * @param file
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> listAllFilesName(File file) {
        return (List<String>) listAllFiles(file, "name", "");
    }

    /**
     * 返回文件夹下所有文件，不包括文件夹。
     *
     * @param file
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<File> listAllFiles(File file) {
        return (List<File>) listAllFiles(file, "file", "");
    }

    /**
     * 返回文件夹下所有特定扩展名的文件名，不包括文件夹，忽略扩展名大小写</br>
     * e.g.   listAllFilesName(new File("a"),"txt");
     *
     * @param file
     * @param extension
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> listAllFilesName(File file, String extension) {
        return listAllFiles(file, "name", extension);
    }

    /**
     * 返回文件夹下所有特定扩展名的文件，不包括文件夹，忽略扩展名大小写
     *
     * @param file
     * @param extension
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<File> listAllFiles(File file, String extension) {
        return listAllFiles(file, "file", extension);
    }

    public static List<Map> listAllFilesSize(File file){
        List<File> files = listAllFiles(file);
        List<Map> result = new ArrayList<>();
        HashMap<String,String> hashMap;
        for (File f:files) {
            hashMap = new HashMap<>();
            hashMap.put("fileName",f.getName());
            hashMap.put("filePath",f.getPath().replace(file.getAbsolutePath(),"").substring(1));
            hashMap.put("filePathRemoveSeparator",hashMap.get("filePath").replaceAll("\\\\","@path@"));
            hashMap.put("fileSize",String.valueOf(f.length()/1024));
            result.add(hashMap);
        }
        return  result;
    }
}
