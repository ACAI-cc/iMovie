package com.gec.system.utils;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;


import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

//视频操作
@Component
public class VodTemplate {

    @Autowired
    private VodConfig vodConfig;

    //初始化
    public DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                vodConfig.getRegionId(),
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }

    //上传视频,返回视频id
    public String uploadVideo(String fileName, InputStream inputStream) {
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret(),
                title,
                fileName,
                inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        return videoId;
    }

    //获取播放凭证函数
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        return initVodClient().getAcsResponse(request);
    }

}
