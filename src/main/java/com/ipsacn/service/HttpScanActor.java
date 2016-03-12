package com.ipsacn.service;

import akka.actor.UntypedActor;
import com.ipsacn.utils.HttpScanResult;
import com.ipsacn.utils.IPv4;
import com.ipsacn.utils.Scan;

/**
 * Created by springdy on 2015/11/9.
 */
public class HttpScanActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof IPv4) {
            IPv4 iPv4 = (IPv4) msg;
            boolean b = Scan.verifyHttp(iPv4.getIp(), iPv4.getPort());
            sender().tell(new HttpScanResult(iPv4, b), getSelf());
        } else {
            unhandled(msg);
        }
    }
}
