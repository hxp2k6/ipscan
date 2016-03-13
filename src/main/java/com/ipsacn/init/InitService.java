package com.ipsacn.init;

import akka.actor.*;
import akka.japi.Creator;
import com.ipsacn.dao.IPScanDao;
import com.ipsacn.service.MasterActor;
import com.ipsacn.utils.IPRegion;
import com.ipsacn.utils.ScanRegion;

import java.util.List;

/**
 * 应用初始化
 * Created by springdy on 2016/3/13.
 */
public class InitService {

    public void init() {
        List<Integer> ports = IPScanDao.getPortList();
        List<IPRegion> regions = IPScanDao.getRegionList();
        ScanRegion region = new ScanRegion(regions, ports);
        ActorSystem system = ActorSystem.create("PiSystem");
        ActorRef master = system.actorOf(Props.create(new Creator<Actor>() {
            public UntypedActor create() {
                return new MasterActor(1000, 1000);
            }
        }), "master");
        master.tell(region, null);
    }

}
