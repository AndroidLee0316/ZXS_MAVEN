package com.pingan.debug.net.downdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;

/**
 *
 * @author yangzijian
 * @date 2018/9/3
 * @des
 * @modify
 **/
public class Util {
    public static void install(Context context, File file) {
        if (!file.exists()) {
            return;
        }
        if (!apkIsOk(context, file.getAbsolutePath())) {
            Toast.makeText(context,"安装包不完整",Toast.LENGTH_SHORT).show();
            file.delete();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        } else {
            Uri uri =
                    FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider",
                            file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param filePath
     * @return
     */
    private static boolean apkIsOk(Context context, String filePath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                result = true;//完整
            }
        } catch (Exception e) {
            result = false;//不完整
        }
        return result;
    }
}
