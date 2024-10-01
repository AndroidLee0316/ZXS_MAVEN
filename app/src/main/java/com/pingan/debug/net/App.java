package com.pingan.debug.net;

import android.app.Application;
import android.text.TextUtils;

import com.pasc.lib.net.HttpDynamicParams;
import com.pasc.lib.net.NetConfig;
import com.pasc.lib.net.NetManager;
import com.pasc.lib.net.download.DownLoadManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SSLContext sslContext = null;
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override public boolean verify(final String hostname, final SSLSession session) {
                return !TextUtils.isEmpty(hostname);
            }
        };
        try {
            sslContext = SSLContext.getInstance("TLS");

                sslContext.init(null, new TrustManager[] { trustManager },
                        new SecureRandom());

        } catch (NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch ( KeyManagementException e){

        }

        NetConfig config = new NetConfig.Builder(this)
                .baseUrl("https://smt-app-stg.pingan.com.cn:58443/")
                .sslContext(sslContext)
                .hostnameVerifier(hostnameVerifier)
                .trustManager(trustManager)
                .isDebug(true)
                .build();
        NetManager.init(config);
        DownLoadManager.getDownInstance().init(this,3,5,0);
        HttpDynamicParams.getInstance ().setDynamicParam (new HttpDynamicParams.DynamicParam () {
            @Override
            public Map<String, String> headers() {
                Map<String,String> headers=new HashMap<> ();
                headers.put ("timestamp",System.currentTimeMillis ()+"");
                headers.put ("token","token"+System.currentTimeMillis ());
                return headers;
            }
        });
    }
}
