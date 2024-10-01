package com.pasc.lib.net.resp;

import com.pasc.lib.net.ApiV2Error;
import com.pasc.lib.net.ExceptionHandler;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * 基本响应Single回调
 * <p>
 * Created by duyuan797 on 2017/3/23.
 */
public abstract class BaseRespObserver<T> implements SingleObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onSuccess(T t) {
    }

    @Override
    public void onError(Throwable throwable) {
        int errorCode = ExceptionHandler.getExceptionWithCode(throwable);
        String msg=ExceptionHandler.handleException(throwable);
        onError(errorCode, msg);

        String errorCodeStr = ExceptionHandler.getExceptionV2WithCode(throwable);
        onV2Error(errorCodeStr, msg);

    }

    public void onError(int code, String msg) {
    }

    public void onV2Error(String code, String msg) {
    }

}
