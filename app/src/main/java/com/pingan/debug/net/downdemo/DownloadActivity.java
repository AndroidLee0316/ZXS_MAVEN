package com.pingan.debug.net.downdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pasc.lib.net.download.DownLoadManager;
import com.pasc.lib.net.download.DownloadInfo;
import com.pasc.lib.net.download.DownloadObserver;
import com.pingan.debug.net.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadActivity extends AppCompatActivity {
    private static final int INSTALL_PACKAGES_REQUESTCODE = 2000;
    private static final int GET_UNKNOWN_APP_SOURCES = 3000;
        final String downLoadUrlTest = "http://gdown.baidu.com/data/wisegame/6e4f792329832d52/yingyongbao_7222130.apk";
//    final String downLoadUrlTest = "http://smt-app-stg.pingan.com.cn/nantongsmt/upload/download.do?name=app/780481a1-3d53-445b-b0cd-41cc6fc22d03.apk";
    DownloadInfo info = new DownloadInfo (downLoadUrlTest, "yingyongbao_7222130.apk", true);
    SeekBar seekBar;
    RecyclerView recyclerView;
    private List<DownloadInfo> downloadInfoList = new ArrayList<> ();
    LoadRecycleViewAdapter adapter;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;

    public void showToast(String ss) {
        Toast.makeText (this, ss, Toast.LENGTH_SHORT).show ();
    }

    private String[] storePermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private boolean isActivityDestroy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        isActivityDestroy = false;
        setContentView (R.layout.activity_download);
        seekBar = findViewById (R.id.seekBar);
        recyclerView = findViewById (R.id.recyclerView);
        adapter = new LoadRecycleViewAdapter (this, downloadInfoList);
        adapter.registerDownObserver ();
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        recyclerView.setAdapter (adapter);
        //禁止刷新动画
        ((SimpleItemAnimator) recyclerView.getItemAnimator ()).setSupportsChangeAnimations (false);
        new Thread (new Runnable () {
            @Override
            public void run() {
                try {
                    final ArrayList<DownloadInfo> downloadInfos = ApkUrlParser.parse (getAssets ().open ("apkurl.xml"));
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            if (!isActivityDestroy) {
                                downloadInfoList.addAll (downloadInfos);
                                adapter.notifyDataSetChanged ();

                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace ();
                }
            }
        }).start ();
        checkSdcard ();

        //POST 表单
//        Map<String, String> headers = new HashMap<> ();
//        headers.put ("x-api-version", "1.2.0");
//        Map<String, String> params = new HashMap<> ();
//        params.put ("jsonData", new Gson ().toJson (new BaseParam<> (new CheckUpdateParam ())));
//        info.method (DownloadInfo.POST)
//                .downloadUrl ("http://smt-app-stg.pingan.com.cn/nantongsmt/appVersion/queryNewVersionInfo.do")
//                .fileName ("my.txt")
//                .headers (headers)
//                .params (params);

        //POST json
//        String jsonData = new Gson ().toJson (new CheckUpdateParam ());
//        info.method (DownloadInfo.POST_JSON)
//                .downloadUrl ("http://cssc-smt-stg3.yun.city.pingan.com/api/platform/appVersion/queryNewVersionInfo")
//                .fileName ("myJson.txt")
//                .headers (headers)
//                .jsonData (jsonData);

    }

    public void viewClick(View view) {
        switch (view.getId ()) {
            case R.id.btnStartDownload:


                if (android8InstallCheck ())
                    DownLoadManager.getDownInstance ().startDownload (info, observer);
                break;
            case R.id.btnPauseDownload:
                DownLoadManager.getDownInstance ().stopDownload (info);
                break;
        }

    }

    DownloadObserver observer = new DownloadObserver () {
        @Override
        public void onDownloadStateProgressed(DownloadInfo updateInfo) {
            switch (updateInfo.downloadState) {
                case DownloadInfo.STATE_DOWNLOADING:
                    seekBar.setProgress (updateInfo.getPercent ());
                    break;
                case DownloadInfo.STATE_ERROR:
                    showToast ("下载错误");
                    break;
                case DownloadInfo.STATE_PAUSED:
                    showToast ("暂停下载");
                    break;
                case DownloadInfo.STATE_FINISH:
                    showToast ("下载完成");
                    File file = new File (updateInfo.getFilePath (getApplicationContext ()));
                    if (file.getName ().endsWith (".apk"))
                        Util.install (getApplicationContext (), file);
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        adapter.unRegisterDownObserver ();
        DownLoadManager.getDownInstance ().stopDownload (info);
        isActivityDestroy = true;

    }


    public void checkSdcard() {
        // todo 特别注意华为手机一定需要sdcard权限，下载后才能安装
        ActivityCompat.requestPermissions (this,
                storePermission,
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);


    }

    boolean android8InstallCheck() {
        /**
         * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
         */
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager ().canRequestPackageInstalls ();
            if (!b) {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_PACKAGES_REQUESTCODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    //                    // contacts-related task you need to do.
                    Log.i ("yzj", "onRequestPermissionsResult suceess");
//                    Toast.makeText(this,"Sdcard授权成功",Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.i ("yzj", "onRequestPermissionsResult fail");
//                    finish ();
//                    Toast.makeText (this, "必须需要Sdcard权限", Toast.LENGTH_SHORT).show ();

                }
                return;
            }
            case INSTALL_PACKAGES_REQUESTCODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText (this, "必须需要安装权限", Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult (intent, GET_UNKNOWN_APP_SOURCES);
                }
                return;
            }
        }
    }

}
