package com.kimpu.prdownloaderex1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimpu.prdownloaderex1.downloader.PRDownloader;
import com.kimpu.prdownloaderex1.downloader.Progress;
import com.kimpu.prdownloaderex1.downloader.request.DownloadRequest;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    List<DownloadRequest> requestList;

    public MyAdapter(Context mContext, List<DownloadRequest> requestList) {
        this.mContext = mContext;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.upadateProgress(requestList.get(position));
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
        }

        public void upadateProgress(DownloadRequest request) {
            request.setOnProgressHandlerListener(new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    Progress progress = (Progress) msg.obj;
                    Log.i("progress", progress.currentBytes + " / " + progress.totalBytes);
                    long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                    //Log.i("progressPercent",FileUtils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                    progressBar.setProgress((int) progressPercent);
                }
            });

        }
    }

    public void notifyData(){
        this.requestList= PRDownloader.getAllRequest();
        notifyDataSetChanged();
    }
}
