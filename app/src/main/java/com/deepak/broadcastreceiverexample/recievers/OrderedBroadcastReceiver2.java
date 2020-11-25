package com.deepak.broadcastreceiverexample.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OrderedBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"OrderedBroadcastReceiver 2 triggered : " + intent.getAction(), Toast.LENGTH_LONG).show();
        Log.d("BroadcastReceiver","OBR 2 triggered:"+intent.getAction());
    }
}
