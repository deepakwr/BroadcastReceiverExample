package com.deepak.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(View view){
        Intent intent = new Intent("com.deepak.broadcastreceiverexample.EXAMPLE_ACTION");
        intent.putExtra("com.deepak.broadcastreceiverexample.EXTRA_TEXT","Broadcast received from: broadcast sender");
        sendBroadcast(intent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String receivedText = intent.getStringExtra("com.deepak.broadcastreceiverexample.EXTRA_TEXT");

            runOnUiThread(() -> {
                ((TextView)findViewById(R.id.txt_message)).setText(receivedText);
            });

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.deepak.broadcastreceiverexample.EXAMPLE_ACTION");
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }


    public void sendBroadcastExplicity(View view){
        Intent intent = new Intent(this,DifferentBroadcastReceiver.class);

        // Alternative  way 1
//        intent.setClass(this,DifferentBroadcastReceiver.class);   //Another way



        sendBroadcast(intent);
    }


    public void sendBroadcastExported(View view){

        // Alternative  way 2
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.deepak.broadcastreceiverexample",
                "com.deepak.broadcastreceiverexample.recievers.ExportedBroadcastReceiver");

        intent.setComponent(componentName);
        sendBroadcast(intent);
    }

    public void sendBTWithExtra(View view){
        Intent intent = new Intent("com.deepak.broadcastreceiverexample.EXPORT_SAMPLE");
//        intent.setPackage("com.deepak.broadcastreceiverexample");
//        sendBroadcast(intent);


        //another way
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(intent,0);
        for(ResolveInfo info: infos){
            ComponentName cn = new ComponentName(info.activityInfo.packageName,info.activityInfo.name);
            intent.setComponent(cn);
            sendBroadcast(intent);
        }
    }

    public void sendBTOrdered(View view){
        Intent intent = new Intent("com.deepak.broadcastreceiverexample.ORDERED_SAMPLE");
        intent.setPackage("com.deepak.broadcastreceiverexample");
        sendOrderedBroadcast(intent,null);
    }
}