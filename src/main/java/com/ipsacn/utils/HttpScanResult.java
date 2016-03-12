package com.ipsacn.utils;

/**
 * Created by springdy on 2015/11/9.
 */
public class HttpScanResult {
    private IPv4 iPv4;

    public IPv4 getiPv4() {
        return iPv4;
    }

    public HttpScanResult(IPv4 iPv4, boolean value) {
        this.iPv4 = iPv4;
        this.value = value;
    }

    public void setiPv4(IPv4 iPv4) {
        this.iPv4 = iPv4;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    private boolean value;

}
