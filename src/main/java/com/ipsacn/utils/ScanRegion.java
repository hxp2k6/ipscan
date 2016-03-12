package com.ipsacn.utils;

import java.util.List;

/**
 * Created by springdy on 2015/11/9.
 */
public class ScanRegion {
    private List<IPRegion> ipRegoins;
    private List<Integer> ports;

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public List<IPRegion> getIpRegoins() {
        return ipRegoins;
    }

    public ScanRegion(List<IPRegion> ipRegoins, List<Integer> ports) {
        this.ipRegoins = ipRegoins;
        this.ports = ports;
    }

    public void setIpRegoins(List<IPRegion> ipRegoins) {
        this.ipRegoins = ipRegoins;
    }
}
