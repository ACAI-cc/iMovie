package com.gec.system.controller;

import com.gec.system.util.OssTemplate;
import com.gec.system.utils.VodTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/admin/system/upload")
@RestController

@CrossOrigin //  处理 跨域问题
public class UploadController{

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private VodTemplate vodTemplate;



    //  http://localhost:8085/admin/system/upload/uploadImage
    // 上传文件 (图片)
    @RequestMapping(value = "/uploadImage")
    public String uploadImage(MultipartFile uploadImage) throws IOException {

        String imageUrl = this.ossTemplate.upload(uploadImage.getOriginalFilename(), uploadImage.getInputStream());

        return imageUrl;
    }

    //  "http://localhost:8085/admin/system/upload/uploadVideo

    // 上传视频操作
    @RequestMapping(value = "/uploadVideo")
    public String uploadVideo(MultipartFile uploadVideo) throws IOException {
        String videoId = this.vodTemplate.uploadVideo(uploadVideo.getOriginalFilename(), uploadVideo.getInputStream());

        System.out.println("+++__-----"+videoId);
        return  videoId;
    }



}