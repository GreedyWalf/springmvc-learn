package com.qs.mvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        try {
            //使用工具类快速将文件写到磁盘中
            FileUtils.writeByteArrayToFile(new File("D:/upload/" + file.getOriginalFilename()),
                    file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "err";
        }
    }
}
