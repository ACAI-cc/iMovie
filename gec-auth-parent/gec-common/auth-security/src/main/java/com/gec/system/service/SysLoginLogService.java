package com.gec.system.service;

public interface SysLoginLogService {

    /**
     *
     * @param username
     * @param status
     * @param ipaddr
     * @param message
     */
    public void recordLoginLog(String username,Integer status,String ipaddr,String message);
}
