package com.pingan.debug.net.downdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pasc.lib.net.download.DownLoadManager;
import com.pasc.lib.net.download.DownloadInfo;
import com.pasc.lib.net.download.DownloadObserver;
import com.pingan.debug.net.R;

import java.io.File;
import java.util.List;

/**
 * Created by YZJ on 2017/6/9: 13.
 * RecycleView 下载适配器
 */

public class LoadRecycleViewAdapter extends RecyclerView.Adapter<LoadRecycleViewAdapter.LoadHolder> implements DownloadObserver {
    private List<DownloadInfo> datas;
    Context context;

    public LoadRecycleViewAdapter(Context context, List<DownloadInfo> datas) {
        this.datas = datas;
        this.context = context;
    }

    /*******ViewHolder*******/
    class LoadHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView appName;
        ProgressBar progress;
        Button pauseOrStart;
        Button install;

        public LoadHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            appName = (TextView) itemView.findViewById(R.id.appName);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            pauseOrStart = (Button) itemView.findViewById(R.id.pauseOrStart);
            install = (Button) itemView.findViewById(R.id.install);
        }

        /****** 更新状态*******/
        public void update(DownloadInfo item) {
            switch (item.downloadState) {
                case DownLoadManager.STATE_NONE:
                    pauseOrStart.setText("下载");
                    break;
                case DownLoadManager.STATE_WAITING:
                    pauseOrStart.setText("等待");
                    break;
                case DownLoadManager.STATE_DOWNLOADING:
                    pauseOrStart.setText("暂停");
                    progress.setProgress(item.getPercent());
                    progress.setMax(100);
                    break;
                case DownLoadManager.STATE_PAUSED:
                    pauseOrStart.setText("继续");
                    break;
                case DownLoadManager.STATE_FINISH:
                    pauseOrStart.setVisibility(View.GONE);
                    install.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                    Util.install(context, new File(item.getFilePath(context)));

                    break;
                case DownLoadManager.STATE_ERROR:
                    pauseOrStart.setText("重试");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public LoadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LoadHolder(LayoutInflater.from(context).inflate(R.layout.loaditem, null));
    }

    @Override
    public void onBindViewHolder(LoadHolder holder, int position) {
        final DownloadInfo item = datas.get(position);
        holder.appName.setText(item.fileName);
        final File tmpFile = new File(item.getFilePath(context));
        if (tmpFile.exists()) {
            holder.pauseOrStart.setVisibility(View.GONE);
            holder.install.setVisibility(View.VISIBLE);
            holder.progress.setVisibility(View.GONE);
        } else {
            holder.pauseOrStart.setVisibility(View.VISIBLE);
            holder.install.setVisibility(View.GONE);
            holder.progress.setVisibility(View.VISIBLE);
            holder.progress.setProgress(item.getPercent());
            holder.progress.setMax(100);
        }

        holder.update(item);
        holder.pauseOrStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!android8InstallCheck()) {
                    return;
                }
                switch (item.downloadState) {
                    case DownLoadManager.STATE_NONE:
                        item.setDownloadState(DownLoadManager.STATE_WAITING);
                        DownLoadManager.getDownInstance().startDownload(item);
                        break;
                    case DownLoadManager.STATE_WAITING:
                        item.setDownloadState(DownLoadManager.STATE_PAUSED);
                        DownLoadManager.getDownInstance().stopDownload(item);
                        break;
                    case DownLoadManager.STATE_DOWNLOADING:
                        item.setDownloadState(DownLoadManager.STATE_PAUSED);
                        DownLoadManager.getDownInstance().stopDownload(item);
                        break;
                    case DownLoadManager.STATE_PAUSED:
                        item.setDownloadState(DownLoadManager.STATE_WAITING);
                        DownLoadManager.getDownInstance().startDownload(item);
                        break;
                    case DownLoadManager.STATE_FINISH:
                        Util.install(context, new File(item.getFilePath(context)));
                        //安装
                        break;
                    case DownLoadManager.STATE_ERROR:
                        item.setDownloadState(DownLoadManager.STATE_WAITING);
                        DownLoadManager.getDownInstance().startDownload(item);
                        break;

                    default:
                        break;
                }
                onDownloadStateProgressed(item);
            }
        });
        holder.install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!android8InstallCheck()) {
                    return;
                }
                Util.install(context, new File(item.getFilePath(context)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /*******注册观察者*******/
    public void registerDownObserver() {
        DownLoadManager.getDownInstance().registerObserver(this);
    }

    /*******注销观察者*******/
    public void unRegisterDownObserver() {
        DownLoadManager.getDownInstance().unRegisterObserver(this);
    }

    @Override
    public void onDownloadStateProgressed(final DownloadInfo updateInfo) {
        final int index = datas.indexOf(updateInfo);
        if (index >= 0) {
            DownloadInfo item = datas.get(index);
            item.updateDownloadInfo(updateInfo);
            notifyItemChanged(index);

        }

    }

    private static final int INSTALL_PACKAGES_REQUESTCODE = 2000;

    boolean android8InstallCheck() {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            /**
             * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
             */
            if (Build.VERSION.SDK_INT >= 26) {
                boolean b = activity.getPackageManager().canRequestPackageInstalls();
                if (!b) {
                    //请求安装未知应用来源的权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_PACKAGES_REQUESTCODE);
                    return false;
                }
                return true;
            } else {
                return true;
            }
        }
        return false;
    }


}
