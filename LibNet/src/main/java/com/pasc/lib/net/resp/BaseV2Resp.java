package com.pasc.lib.net.resp;

import com.google.gson.annotations.SerializedName;

/**
 * 后台 code 改成了 string
 */
public class BaseV2Resp<T> {

    @SerializedName("code") public String code;

    @SerializedName("msg") public String msg;

    @SerializedName("data") public T data;

    @Override public String toString() {
        return "BaseResp{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
