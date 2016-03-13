package com.ipsacn.service;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.ipsacn.utils.HttpScanResult;
import com.ipsacn.utils.IPv4;
import com.ipsacn.utils.ScanURL;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Created by springdy on 2015/11/9.
 */
public class HttpScanActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof IPv4) {
            IPv4 iPv4 = (IPv4) msg;
            boolean b = verifyHttp(iPv4.getIp(), iPv4.getPort());
            sender().tell(new HttpScanResult(iPv4, b), getSelf());
        } else {
            unhandled(msg);
        }
    }

    /**
     * http代理认证代理端口是否可用
     */
    private boolean verifyHttp(String ip, int port) {
        boolean b = false;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        InputStream in = null;
        try {
            URL url = new URL(ScanURL.VERIFY_URL);
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
            log.info(e.getMessage(), e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.info(e.getMessage(), e);
                }
            }
        }
        return b;
    }
}
