package com.ipsacn.service;

import akka.actor.*;
import akka.japi.Creator;
import com.ipsacn.utils.IPRegion;
import com.ipsacn.utils.ScanRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by springdy on 2015/11/9.
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> ports = new ArrayList<Integer>();
        ports.add(80);
        ports.add(3128);
        ports.add(8088);
        ports.add(8080);
        ports.add(8081);
        ports.add(9797);
        ports.add(8090);
        ports.add(808);
        ports.add(9999);
        ports.add(6666);
        List<IPRegion> regions = new ArrayList<IPRegion>();
        regions.add(new IPRegion("14.20.0.0","14.20.255.255"));
        regions.add(new IPRegion("14.120.0.0","14.120.255.255"));
        regions.add(new IPRegion("14.122.0.0","14.122.255.255"));
        regions.add(new IPRegion("14.124.0.0","14.124.255.255"));
        regions.add(new IPRegion("14.153.0.0","14.153.116.255"));
        regions.add(new IPRegion("14.153.118.0","14.155.255.255"));
        ScanRegion region = new ScanRegion(regions,ports);
//        ScanRegion region = new ScanRegion("192.168.6.1","192.168.7.1",ports);
        ActorSystem system = ActorSystem.create("PiSystem");
        ActorRef master = system.actorOf(Props.create(new Creator<Actor>() {
            public UntypedActor create() {
                return  new MasterActor(1000,1000);
            }
        }), "master");
        master.tell(region,null);
    }
}
