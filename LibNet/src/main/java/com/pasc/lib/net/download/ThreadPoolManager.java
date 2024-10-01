package com.pasc.lib.net.download;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 下载线程池
 * Created by YZJ on 2017/6/8: 19.
 */
public class ThreadPoolManager {
    private ThreadPoolExecutor executor ;
    private static ThreadPoolManager instance;
    private ThreadPoolManager(){}

    public static ThreadPoolManager getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    public  void init(int corePoolSize,
                      int maximumPoolSize,
                      long keepAliveTime){

        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

    }

    /*** 添加一个任务**/
    public  void execute(BaseLoadTask task) {
        if (executor==null){
            Log.e("pascDownload","必须先初始化下载线程池 ps DownLoadManager.getDownInstance().init(this,3,5,0);");
            return;
        }
        if (task != null && !task.isCancle() && !executor.isShutdown()) {
            executor.execute(task);
        }
    }
    /*** 取消下载任务*/
    public  void cancel(BaseLoadTask task) {
        if (executor==null){
            Log.e("pascDownload","必须先初始化下载线程池 ps DownLoadManager.getDownInstance().init(this,3,5,0);");
            return;
        }
        if (task != null && !executor.isShutdown()){
            task.cancle();
            executor.getQueue().remove(task);
        }
    }
}
