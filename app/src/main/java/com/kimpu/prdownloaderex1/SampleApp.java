package com.kimpu.prdownloaderex1;

import android.app.Application;

import com.kimpu.prdownloaderex1.downloader.PRDownloader;
import com.kimpu.prdownloaderex1.downloader.PRDownloaderConfig;


public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(this, config);
    }

}