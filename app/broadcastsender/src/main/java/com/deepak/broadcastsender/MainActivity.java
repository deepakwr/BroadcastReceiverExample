package com.deepak.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
}