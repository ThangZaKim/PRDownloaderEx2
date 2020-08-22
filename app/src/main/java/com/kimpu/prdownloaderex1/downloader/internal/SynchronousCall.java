package com.kimpu.prdownloaderex1.downloader.internal;


import com.kimpu.prdownloaderex1.downloader.Response;
import com.kimpu.prdownloaderex1.downloader.request.DownloadRequest;

public class SynchronousCall {

    public final DownloadRequest request;

    public SynchronousCall(DownloadRequest request) {
        this.request = request;
    }

    public Response execute() {
        DownloadTask downloadTask = DownloadTask.create(request);
        return downloadTask.run();
    }

}
