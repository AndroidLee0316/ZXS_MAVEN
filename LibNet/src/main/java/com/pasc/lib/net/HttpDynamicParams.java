package com.pasc.lib.net;

import java.util.Map;

/**
 * 运行时，每个请求 的token  timestamp  网络类型 是经常变化的
 * HttpCommonParams 需要每次更新都设置
 * 但是对于类似 时间戳timestamp ，不可能每次都去修改 HttpCommonParams
 */

public class HttpDynamicParams {

    private HttpDynamicParams() {
    }
    private static class SingletonHolder {
        private static final HttpDynamicParams INSTANCE = new HttpDynamicParams ();
    }

    public static HttpDynamicParams getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DynamicParam dynamicParam;

    public void setDynamicParam(DynamicParam dynamicParam) {
        this.dynamicParam = dynamicParam;
    }
    public  Map<String, String> getHeaders(){
        if (dynamicParam!=null){
            return dynamicParam.headers ();
        }
        return null;
    }
    public  Map<String, String> getParams(){
        if (dynamicParam!=null){
            return dynamicParam.params ();
        }
        return null;
    }

    public static abstract class DynamicParam {
        public abstract Map<String, String> headers();
        public  Map<String, String> params(){
            return null;
        }
    }


}