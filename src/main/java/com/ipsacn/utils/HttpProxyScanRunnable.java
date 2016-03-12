package com.ipsacn.utils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Calendar;

/**
 * Created by springdy on 2015/11/6.
 */
class HttpProxyScanRunnable implements Runnable {
    private final static String murl = "http://home.baidu.com/resource/r/home/menu.js";
    private String ip;
    private int port;

    public HttpProxyScanRunnable(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        long start = Calendar.getInstance().getTimeInMillis();
        boolean b = Scan.verifyHttp(ip,port);
        if(b) {
            long end = Calendar.getInstance().getTimeInMillis();
            System.out.println("ip:" + ip + ",port:" + port + " is proxy open" + " connect time:" + (end - start));
        }
    }
}