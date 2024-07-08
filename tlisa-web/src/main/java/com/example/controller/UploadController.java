package com.example.controller;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    /**
     * 上传文件
     * @param username
     * @param age
     * @param image
     * @return
     */
    @PostMapping
    public Result upload(MultipartFile image, HttpSession session) throws Exception {
        log.info("上传文件:{}",image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（防止前端提交的文件名重复）
        //uuid-通用唯一识别码
        Integer index= originalFilename.lastIndexOf(".");//获取最后一个点的索引
        String extname=originalFilename.substring(index);//获取原始文件名后缀
        String newFileName= UUID.randomUUID().toString()+extname;

        log.info("新的文件名：{}",newFileName);

        String filePath="E:/DevelopProject/itheima_web_project/tlisa-web/src/main/resources/image/"+newFileName;

        // 将 newFileName 存入 Session 中
        session.setAttribute("image", filePath);

        //将前端提交的文件存储到本地 /resources/image
        image.transferTo(new File(filePath));
        return Result.success();
    }
}
