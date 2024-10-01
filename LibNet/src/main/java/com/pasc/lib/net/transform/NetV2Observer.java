package com.pasc.lib.net.transform;

import com.pasc.lib.net.resp.BaseV2Resp;

/**
 * @author yangzijian
 * @date 2018/9/17
 * @des
 * @modify
 **/
public interface NetV2Observer {
    void notifyErrorNet(BaseV2Resp baseResp);
}
