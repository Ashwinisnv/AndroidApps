package com.ashwinisnv.broadcastapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ashwinivishwas on 2/25/18.
 */

public class MyReceiverfromApp1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Intent activityIntent = new Intent(context, MainActivity.class);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activityIntent.putExtra("msg1", intent.getStringExtra("message1"));
            activityIntent.putExtra("msg2", intent.getStringExtra("message2"));
            context.startActivity(activityIntent);
            if(MainActivity.txt != null && MainActivity.crt !=null) {

                MainActivity.txt.setText(intent.getStringExtra("message1"));
                MainActivity.crt.setText(intent.getStringExtra("message2"));
            }
        }
}
