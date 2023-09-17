package com.gec.system.utils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//oss存储配置
@Configuration
@ConfigurationProperties("al.vod")
@Data
public class VodConfig {
    //区域
    private String regionId;
    //访问id
    private String accessKeyId;
    //访问秘钥
    private String accessKeySecret;
}
