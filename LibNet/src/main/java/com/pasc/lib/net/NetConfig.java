package com.pasc.lib.net;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;

/**
 * Created by huanglihou519 on 2018/9/2.
 */
public final class NetConfig {
  public String baseUrl;
  public Gson gson;
  public boolean isDebug;
  public Map<String, String> headers;
  public SSLContext sslContext;
  public X509TrustManager trustManager;
  public HostnameVerifier hostnameVerifier;
  public Context context;
  public CertificatePinner certificatePinner;
  public List<Interceptor> interceptors;


  private NetConfig(Builder builder) {
    baseUrl = builder.baseUrl;
    gson = builder.gson;
    isDebug = builder.isDebug;
    headers = builder.headers;
    sslContext = builder.sslContext;
    trustManager = builder.trustManager;
    hostnameVerifier = builder.hostnameVerifier;
    context = builder.context;
    certificatePinner = builder.certificatePinner;
    interceptors = builder.interceptors;
  }

  public static class Builder {
    final Context context;
    private Gson gson;
    private boolean isDebug = false;
    private Map<String, String> headers;
    private SSLContext sslContext;
    private X509TrustManager trustManager;
    private HostnameVerifier hostnameVerifier;
    private String baseUrl;
    private CertificatePinner certificatePinner;
    private List<Interceptor> interceptors = new ArrayList<>();

    public Builder(Context context) {
      this.context = context;
    }

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder gson(Gson gson) {
      this.gson = gson;
      return this;
    }

    public Builder isDebug(boolean isDebug) {
      this.isDebug = isDebug;
      return this;
    }

    public Builder addInterceptor(Interceptor interceptor){
      interceptors.add(interceptor);
      return this;
    }

    public Builder headers(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder sslContext(SSLContext sslContext) {
      this.sslContext = sslContext;
      return this;
    }

    public Builder trustManager(X509TrustManager trustManager) {
      this.trustManager = trustManager;
      return this;
    }

    public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
      this.hostnameVerifier = hostnameVerifier;
      return this;
    }

    public Builder certificatePinner(CertificatePinner certificatePinner) {
      this.certificatePinner = certificatePinner;
      return this;
    }

    public NetConfig build() {
      if (context == null) {
        throw new IllegalArgumentException("context 不能为空");
      }
      if (TextUtils.isEmpty(baseUrl)) {
        throw new IllegalArgumentException("baseUrl 不能为空");
      }
      return new NetConfig(this);
    }
  }
}
