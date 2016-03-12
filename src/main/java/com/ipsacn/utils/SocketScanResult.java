package com.ipsacn.utils;

import com.springdy.utils.IPv4;

/**
 * Created by springdy on 2015/11/9.
 */
public class SocketScanResult {
    private IPv4 iPv4;
    private boolean value;

    public IPv4 getiPv4() {
        return iPv4;
    }

    public SocketScanResult(IPv4 iPv4, boolean value) {
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

}
