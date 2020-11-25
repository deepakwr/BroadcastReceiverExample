package com.deepak.broadcastreceiverexample.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //permission in manifest - android.permission.RECEIVE_BOOT_COMPLETED
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Toast.makeText(context,"Boot completed",Toast.LENGTH_LONG).show();
        }

        //permission in manifest android.net.conn.CONNECTIVITY_CHANGE  -   Only works on device API lower than 26
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            Toast.makeText(context,"Connectivity Changed : " + intent.getExtras().toString(),Toast.LENGTH_LONG);
        }

    }

}
