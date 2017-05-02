package com.example.servicefordownload;

/**
 * Created by Administrator on 2017/4/3.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
