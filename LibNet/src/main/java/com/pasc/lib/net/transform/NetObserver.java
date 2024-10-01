package com.pasc.lib.net.transform;

import com.pasc.lib.net.resp.BaseResp;

/**
 * @author yangzijian
 * @date 2018/9/17
 * @des
 * @modify
 **/
public interface NetObserver {
    void notifyErrorNet(BaseResp baseResp);
}
