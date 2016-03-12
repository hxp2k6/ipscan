package com.ipsacn.service;

import akka.actor.UntypedActor;
import com.ipsacn.utils.IPv4;
import com.ipsacn.utils.Scan;
import com.ipsacn.utils.SocketScanResult;

/**
 * Created by springdy on 2015/11/9.
 */
public class SocketScanActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof IPv4){
            IPv4 iPv4 = (IPv4) msg;
            boolean b = Scan.verifySocket(iPv4.getIp(), iPv4.getPort());
            sender().tell(new SocketScanResult(iPv4,b),getSelf());
        }else{
            unhandled(msg);
        }
    }
}
