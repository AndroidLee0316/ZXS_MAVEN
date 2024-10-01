package com.pasc.lib.net.resp;

import com.pasc.lib.net.ExceptionHandler;

import io.reactivex.subscribers.DefaultSubscriber;

/**
 * 基本响应回调
 * <p>
 * Created by duyuan797 on 2017/3/23.
 */
public abstract class BaseRespFlowableObserver<T> extends DefaultSubscriber<T> {

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable throwable) {
        int errorCode = ExceptionHandler.getExceptionWithCode(throwable);
        String msg=ExceptionHandler.handleException(throwable);
        onError(errorCode, msg);

        String errorCodeStr = ExceptionHandler.getExceptionV2WithCode(throwable);
        onV2Error(errorCodeStr, msg);
    }

    public  void onError(int code, String msg){}
    public  void onV2Error(String code, String msg){}


    @Override
    public void onComplete() {
    }
}
