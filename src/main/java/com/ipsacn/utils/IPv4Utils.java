package com.ipsacn.utils;

/**
 * Created by springdy on 2015/11/6.
 * ip long值和字符串的转换
 */
public class IPv4Utils {

    /**
     * 将字符串的ip 转换为long
    */
    public static long ipToLong(String ip) {
        long ipNum = 0;
        try {
            if (ip != null) {
                String ips[] = ip.split("\\.");
                for (int i = 0; i < ips.length; i++) {
                    int k = Integer.parseInt(ips[i]);
                    ipNum = ipNum + k * (1L << ((3 - i) * 8));
                }
            }
        } catch (Exception e) {
        }
        return ipNum;
    }

    /**
     * 将long的ip 转换为字符串
     */
    public static String longToIp(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

}
