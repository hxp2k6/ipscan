package com.ipsacn.service;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.ipsacn.utils.IPv4;
import com.ipsacn.utils.SocketScanResult;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by springdy on 2015/11/9.
 */
public class SocketScanActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof IPv4) {
            IPv4 iPv4 = (IPv4) msg;
            boolean b = verifySocket(iPv4.getIp(), iPv4.getPort());
            sender().tell(new SocketScanResult(iPv4, b), getSelf());
        } else {
            unhandled(msg);
        }
    }

    /**
     * 验证徵端口是否可用
     */
    public boolean verifySocket(String ip, int port) {
        boolean result = false;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            log.error("ip is invalid." + ip);
            return result;
        }
//        if (ip.contains("119.147.161.5"))
//            System.out.println("scan ip:" + ip + ",port：" + port);
        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(inetAddress, port), 300);
            result = true;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } finally {
            if (s != null && !s.isClosed()) {
                try {
                    s.close();
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
        return result;
    }
}
