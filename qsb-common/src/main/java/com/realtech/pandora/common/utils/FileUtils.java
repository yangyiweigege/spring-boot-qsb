package com.realtech.pandora.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhuqq
 */
public class FileUtils {
    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
       /* String location = DateUtil.format(new Date(), "yyyy-MM-dd") ;
        path +=location;*/
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String uuid = getUUID32();
        //去掉原文件名 只要自己随机生成的名
        int suffixIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
        String fileName = uuid + multipartFile.getOriginalFilename().substring(suffixIndex);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator  + fileName));
        byte[] bs = new byte[1024*10];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
