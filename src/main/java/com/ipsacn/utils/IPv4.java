package com.ipsacn.utils;

/**
 * Created by springdy on 2015/11/9.
 */
public class IPv4 {
    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public IPv4(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
