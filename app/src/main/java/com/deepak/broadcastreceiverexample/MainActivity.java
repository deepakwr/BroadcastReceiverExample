package com.deepak.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.deepak.broadcastreceiverexample.recievers.ExampleBroadcastReceiver;
import com.deepak.broadcastreceiverexample.recievers.OrderedBroadcastReceiver1;

public class MainActivity extends AppCompatActivity {

    /*
    1. Below is for creating dynamic reciever.
    2. static receiver require mentioned in manifest
     */
    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();

    OrderedBroadcastReceiver1 orderedBroadcastReceiver1 = new OrderedBroadcastReceiver1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(getPackageName() + ".EXAMPLE_ACTION");
        registerReceiver(exampleBroadcastReceiver,filter);



        IntentFilter orderedIntentFilter = new IntentFilter("com.deepak.broadcastreceiverexample.ORDERED_SAMPLE");
        //Higher number gets first priority :   IntentFilter.SYSTEM_HIGH_PRIORITY(1000) to IntentFilter.SYSTEM_LOW_PRIORITY(-1000)
        orderedIntentFilter.setPriority(1);

        registerReceiver(orderedBroadcastReceiver1,orderedIntentFilter,android.Manifest.permission.VIBRATE,null);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        /*
//        This can be called at resume/pause/onstart level in aactivity scope or Application scope.
//        Ideally it depends on use case if you want receiver to capture even if on background and active state.
//        */
//        registerReceiver(exampleBroadcastReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(exampleBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exampleBroadcastReceiver);
        unregisterReceiver(orderedBroadcastReceiver1);
    }
}