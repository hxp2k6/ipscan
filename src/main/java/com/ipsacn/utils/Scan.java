package com.ipsacn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Created by springdy on 2015/11/9.
 */
public class Scan {
    private final static String murl = "http://home.baidu.com/resource/r/home/menu.js";
    /**
     * 验证徵端口是否可用
    */
    public static boolean verifySocket(String ip,int port){
//        System.out.println("socket:"+ip);
        boolean result = false;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            System.out.println("ip is invalid." + ip);
            return  result;
        }
        if(ip.contains("119.147.161.5"))
            System.out.println("scan ip:"+ip+",port："+port);
        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(inetAddress, port), 300);
            result = true;
        } catch (IOException e) {
//            e.printStackTrace();
        }finally {
            if(s != null && !s.isClosed()){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /*
    * http代理认证代理端口是否可用
    * */
    public static boolean verifyHttp(String ip,int port){
//        System.out.println("http:"+ip);
        boolean b = false;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        InputStream in = null;
        try {
            URL url = new URL(murl);
            URLConnection conn = url.openConnection(proxy);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;
            httpUrlConnection.setConnectTimeout(2000);
            httpUrlConnection.setReadTimeout(5000);
            httpUrlConnection.setUseCaches(false);
            in = httpUrlConnection.getInputStream();
            byte[] buffer = new byte[32];
            in.read(buffer);
            String result = new String(buffer);
            if (result.contains("leftMenu(")) {
                b = true;
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }
}
