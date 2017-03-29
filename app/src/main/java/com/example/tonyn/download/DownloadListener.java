package com.example.tonyn.download;

/**
 * Created by tonyn on 2017/1/20.
 */

public interface DownloadListener {
    void onProgress(int progress);//通知当前下载进度
    void onSuccess();
    void onFailed();
    void onPaused();//用于通知下载暂停事件
    void onCanceled();//通知下再取消事件
}
