package com.ipsacn.utils;

/**
 * Created by springdy on 2015/11/10.
 */
public class IPRegion {
    private String startIP;
    private String endIP;

    public String getStartIP() {
        return startIP;
    }

    public IPRegion(String startIP, String endIP) {
        this.startIP = startIP;
        this.endIP = endIP;
    }

    public void setStartIP(String startIP) {
        this.startIP = startIP;
    }

    public String getEndIP() {
        return endIP;
    }

    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }
}
