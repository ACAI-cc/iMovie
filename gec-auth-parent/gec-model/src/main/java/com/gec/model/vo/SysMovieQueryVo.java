package com.gec.model.vo;

import java.io.Serializable;

public class SysMovieQueryVo implements Serializable {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /// 影视名称
    private String name;
    // 影视类型
    private Integer cid;
}
