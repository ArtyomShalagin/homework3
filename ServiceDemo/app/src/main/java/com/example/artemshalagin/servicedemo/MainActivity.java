package com.example.artemshalagin.servicedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends Activity {

    final static String imageUrl = "https://photolium.ru/uploads/posts/2011-11/1321454991_007.jpg";
    final static String BROADCAST_NAME = "SERVICEDEMO_IMAGE_LOADED";

    ImageView imageView;
    TextView errorTextView;
    BroadcastReceiver receiver;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_view);
        errorTextView = (TextView) findViewById(R.id.error_text_view);
        final String imageFilename = getFilesDir().getPath() + "/image.png";

        boolean imageSuccess = trySetImage(imageFilename);
        if (imageSuccess) {
            imageView.setVisibility(VISIBLE);
            errorTextView.setVisibility(GONE);
        }

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                trySetImage(imageFilename);
                imageView.setVisibility(VISIBLE);
                errorTextView.setVisibility(GONE);
            }
        };
        registerReceiver(receiver, new IntentFilter(BROADCAST_NAME));
    }

    boolean trySetImage(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}