package com.ipsacn.utils;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;

/**
 * Created by springdy on 2015/11/6.
 */
public class SocketScanRunnable implements Runnable {
    private String ip;
    private int port;
    private ExecutorService executor;

    public SocketScanRunnable(String ip, int port,ExecutorService executor) {
        this.port = port;
        this.ip = ip;
        this.executor = executor;
    }

    public void run() {
        long start = Calendar.getInstance().getTimeInMillis();
        if(ip.contains("119.147.161.5"))
            System.out.println("scan ip:"+ip+",port："+port);
        boolean b = Scan.verifySocket(ip,port);
        if(b){
            executor.execute(new HttpProxyScanRunnable(ip,port));
        }
    }
}