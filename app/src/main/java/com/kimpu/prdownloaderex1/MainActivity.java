package com.kimpu.prdownloaderex1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kimpu.prdownloaderex1.downloader.Error;
import com.kimpu.prdownloaderex1.downloader.OnDownloadListener;
import com.kimpu.prdownloaderex1.downloader.PRDownloader;
import com.kimpu.prdownloaderex1.downloader.request.DownloadRequest;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static String dirPath;

    String firstUrl = "https://www.tutorialspoint.com/java/java_tutorial.pdf";
    String secondUrl = "http://l5uaqpp1o6.pdcdn2.top/dl2.php?id=185771881&h=0db0f2eae168c600917807d193abdf4a&u=cache&ext=pdf&n=Java%20for%20absolute%20beginners%20learn%20to%20program%20the%20fundamentals%20the%20java%209%20way";

    int count = 0;

    RecyclerView rvMain;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i("tag", "Permission Granted");
            } else {
                requestPermission(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
            }
        } else { //you dont need to worry ic_about these stuff below api level 23
            Log.e("Permission error", "You already have the permission");
        }

        dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        myAdapter = new MyAdapter(this, new ArrayList<DownloadRequest>());
        rvMain = findViewById(R.id.rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(myAdapter);
        myAdapter.notifyData();

    }

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 1);
    }


    public void startDownloadOne(View view) {
        PRDownloader.download(firstUrl, dirPath, "FirstPDF.pdf")
                .build()
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {

                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
        myAdapter.notifyData();

    }

    public void startDownloadTwo(View view) {
        PRDownloader.download(secondUrl, dirPath, "SecondPDF.pdf")
                .build()
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {

                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
        myAdapter.notifyData();
    }
}
