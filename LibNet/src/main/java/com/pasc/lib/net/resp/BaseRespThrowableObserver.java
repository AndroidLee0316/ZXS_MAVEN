package com.pasc.lib.net.resp;


import com.pasc.lib.net.ExceptionHandler;

import io.reactivex.functions.Consumer;

/**
 * 基本响应Single回调
 * <p>
 * Created by duyuan797 on 2017/3/23.ase
 */
public abstract class BaseRespThrowableObserver implements Consumer<Throwable> {
    @Override
    public void accept(Throwable throwable) throws Exception {

        int errorCode = ExceptionHandler.getExceptionWithCode(throwable);
        String msg=ExceptionHandler.handleException(throwable);
        onError(errorCode, msg);

        String errorCodeStr = ExceptionHandler.getExceptionV2WithCode(throwable);
        onV2Error(errorCodeStr, msg);
    }

    public  void onError(int errorCode, String errorMsg){}
    public  void onV2Error(String errorCode, String errorMsg){}

}
