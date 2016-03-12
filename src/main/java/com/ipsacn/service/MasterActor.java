package com.ipsacn.service;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.ipsacn.utils.*;

/**
 * Created by springdy on 2015/11/9.
 */
public class MasterActor extends UntypedActor {
    private ActorRef socketActorRef;
    private ActorRef httpActorRef;
    private long start ;
    private int socketSendNums = 0;
    private int socketReceiveNums = 0;
    private int httpSendNums = 0;
    private int httpReceiveNums = 0;

    public MasterActor(int socketWorkNums, int httpWorkNums) {
        socketActorRef = getContext().actorOf(Props.create(SocketScanActor.class).withRouter(new RoundRobinPool(socketWorkNums)), "socketScanRouter");
        httpActorRef = getContext().actorOf(Props.create(HttpScanActor.class).withRouter(new RoundRobinPool(httpWorkNums)), "httpScanRouter");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof ScanRegion) {
            start = System.currentTimeMillis();
            startWork((ScanRegion) msg);
        } else if (msg instanceof SocketScanResult) {
            //处理socket验证返回结果
            SocketScanResult result = (SocketScanResult) msg;
            socketReceiveNums ++;
            if(result.isValue()){
                httpSendNums ++;
                httpActorRef.tell(result.getiPv4(),getSelf());
            }
            if(socketReceiveNums == socketSendNums && httpSendNums == 0){
                getContext().stop(getSelf());
                System.out.println("Scan over socket.cost:" + (System.currentTimeMillis() - start));
            }
        } else if (msg instanceof HttpScanResult) {
            //处理http返回验证结果
            HttpScanResult result = (HttpScanResult) msg;
            httpReceiveNums ++;
            if(null!=result && result.isValue()) {
                System.out.println("ip;" + result.getiPv4().getIp() + ",port:" + result.getiPv4().getPort() + "cost time:" + (System.currentTimeMillis() - start));
            }
            if(socketReceiveNums == socketSendNums && httpReceiveNums == httpSendNums ){
                System.out.println("Scan over http.cost:" + (System.currentTimeMillis() - start)+ ",httpSendNums"+httpSendNums);
                getContext().stop(getSelf());
                getContext().system().shutdown();
            }
        } else {
            unhandled(msg);
        }
    }

    private void startWork(ScanRegion region) {
        for(IPRegion ipRegion:region.getIpRegoins()) {
            long ip1 = IPv4Utils.ipToLong(ipRegion.getStartIP());
            long ip2 = IPv4Utils.ipToLong(ipRegion.getEndIP());
            long s = System.currentTimeMillis();
            for (long i = ip1; i < ip2; i++) {
                for (Integer port : region.getPorts()) {
                    socketSendNums++;
                    String ip = IPv4Utils.longToIp(i);
                    socketActorRef.tell(new IPv4(ip, port), getSelf());
                }
            }
        }
    }
}
