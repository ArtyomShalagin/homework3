package com.example.artemshalagin.servicedemo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class ImageService extends IntentService {

    public ImageService() {
        super("ImageDownloadService");
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            File f = new File(getFilesDir(), "image.png");
            if (f.exists()) {
                return;
            }
            URL url = new URL(intent.getStringExtra(getPackageName() + ".image_url"));
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            FileOutputStream fout = openFileOutput("image.png", Context.MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.close();
            sendBroadcast(new Intent(MainActivity.BROADCAST_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
