package com.deepak.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.deepak.broadcastreceiverexample.recievers.ExampleBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    /*
    1. Below is for creating dynamic reciever.
    2. static receiver require mentioned in manifest
     */
    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        /*
        This can be called at resume/pause level in aactivity scope or Application scope.
        Ideally it depends on use case if you want receiver to capture even if on background and active state.
        */
        registerReceiver(exampleBroadcastReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
    }
}