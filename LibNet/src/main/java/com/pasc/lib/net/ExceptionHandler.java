package com.pasc.lib.net;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import java.net.SocketTimeoutException;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;

/**
 * Created by duyuan797 on 17/5/19.
 */

public class ExceptionHandler {

    public static final String CONNECT_EXCEPTION = "当前网络不佳，请稍后重试";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请稍后重试";
    public static final String MALFORMED_JSON_EXCEPTION = "数据解析异常";

    public static String handleException(Throwable throwable) {
        if (throwable == null) {
            return CONNECT_EXCEPTION;
        }
        if (throwable instanceof NoSuchElementException || throwable instanceof CancellationException) {
            return CONNECT_EXCEPTION;
        } else if (throwable instanceof MalformedJsonException || throwable instanceof JsonSyntaxException) {
            return MALFORMED_JSON_EXCEPTION;
        } else if (throwable instanceof SocketTimeoutException) {
            return SOCKET_TIMEOUT_EXCEPTION;
        } else if (throwable instanceof ApiError) {
            return throwable.getMessage();
        }else if (throwable instanceof ApiV2Error) {
            return throwable.getMessage();
        } else {
            return CONNECT_EXCEPTION;

        }
    }

    public static int getExceptionWithCode(Throwable throwable) {
        if (throwable==null){
            return -1;
        }
        if (throwable instanceof ApiError) {
            ApiError apiError = (ApiError) throwable;
            return apiError.getCode();
        } else {
            return -1;
        }
    }

    public static String getExceptionV2WithCode(Throwable throwable) {
        if (throwable==null){
            return "-1";
        }
        if (throwable instanceof ApiV2Error) {
            ApiV2Error apiError = (ApiV2Error) throwable;
            return apiError.getCode();
        } else {
            return "-1";
        }
    }
}
