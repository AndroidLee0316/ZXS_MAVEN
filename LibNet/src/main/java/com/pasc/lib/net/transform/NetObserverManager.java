package com.pasc.lib.net.transform;

import com.pasc.lib.net.resp.BaseResp;

/**
 * @author yangzijian
 * @date 2018/9/17
 * @des
 * @modify
 **/
public class NetObserverManager {
    private NetObserver netObserver;

    private NetObserverManager() {
    }

    private volatile static NetObserverManager instance;

    public static NetObserverManager getInstance() {
        if (instance == null) {
            synchronized (NetObserverManager.class) {
                if (instance == null) {
                    instance = new NetObserverManager();
                }
            }
        }
        return instance;
    }

    public synchronized void registerObserver(NetObserver netObserver) {
        this.netObserver = netObserver;
    }

    public synchronized void unRegisterObserver() {
        this.netObserver = null;
    }

    public synchronized void notifyObserver(BaseResp baseResp){
        if (netObserver!=null && baseResp!=null){
            netObserver.notifyErrorNet(baseResp);
        }
    }

}
