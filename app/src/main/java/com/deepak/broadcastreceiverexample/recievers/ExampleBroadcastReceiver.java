package com.deepak.broadcastreceiverexample.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // Static receiver
        //permission in manifest - android.permission.RECEIVE_BOOT_COMPLETED
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Toast.makeText(context,"Boot completed",Toast.LENGTH_LONG).show();
        }

        //permission in manifest android.net.conn.CONNECTIVITY_CHANGE  -   Only works on device API lower than 26
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            boolean noConnetivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if(noConnetivity){
                Toast.makeText(context,"No connectivity",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"Connected",Toast.LENGTH_LONG).show();

            }
        }

        String EXAMPLE_ACTION_NAME = context.getPackageName() + ".EXAMPLE_ACTION";
        if(EXAMPLE_ACTION_NAME.equals(intent.getAction())){
            String receivedText = intent.getStringExtra(context.getPackageName() + ".EXTRA_TEXT");
            Toast.makeText(context,"Example of custom receiver: " + receivedText,Toast.LENGTH_LONG).show();
        }

    }

}
