package com.ashwinisnv.broadcastapp1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ashwinivishwas on 2/27/18.
 */

public class MyReceiverfromApp2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityIntent.putExtra("msg3",intent.getStringExtra("message3"));
        context.startActivity(activityIntent);
    }
}
