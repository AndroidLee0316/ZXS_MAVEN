package com.pasc.lib.net.transform;

import com.pasc.lib.net.resp.BaseResp;
import com.pasc.lib.net.resp.BaseV2Resp;

/**
 * @author yangzijian
 * @date 2018/9/17
 * @des
 * @modify
 **/
public class NetV2ObserverManager {
    private NetV2Observer netObserver;

    private NetV2ObserverManager() {
    }

    private volatile static NetV2ObserverManager instance;

    public static NetV2ObserverManager getInstance() {
        if (instance == null) {
            synchronized (NetV2ObserverManager.class) {
                if (instance == null) {
                    instance = new NetV2ObserverManager();
                }
            }
        }
        return instance;
    }

    public synchronized void registerObserver(NetV2Observer netObserver) {
        this.netObserver = netObserver;
    }

    public synchronized void unRegisterObserver() {
        this.netObserver = null;
    }

    public synchronized void notifyObserver(BaseV2Resp baseResp){
        if (netObserver!=null && baseResp!=null){
            netObserver.notifyErrorNet(baseResp);
        }
    }

}
