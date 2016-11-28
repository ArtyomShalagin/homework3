package com.example.artemshalagin.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ImageService.class);
        context.startService(i.putExtra(context.getPackageName() + ".image_url", MainActivity.imageUrl));
    }
}
