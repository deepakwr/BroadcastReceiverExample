package com.deepak.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.deepak.broadcastreceiverexample.recievers.AlertReceiver;
import com.deepak.broadcastreceiverexample.recievers.ExampleBroadcastReceiver;
import com.deepak.broadcastreceiverexample.recievers.OrderedBroadcastReceiver1;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    /*
    1. Below is for creating dynamic reciever.
    2. static receiver require mentioned in manifest
     */
    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();

    OrderedBroadcastReceiver1 orderedBroadcastReceiver1 = new OrderedBroadcastReceiver1();

    TimePickerDialog dialogFragment  =null;

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



    public void setAlarm(View view){
        if(dialogFragment==null){
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            dialogFragment= new TimePickerDialog(this,this,mHour,mMinute,false);
        }
        dialogFragment.show();
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlaram(c);

    }

    private void startAlaram(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent  = new Intent(this, AlertReceiver.class);
        int requestCode = 1;//unique code
        int flags =0;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent,flags);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    public void cancelAlarm(View view){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent  = new Intent(this, AlertReceiver.class);
        int requestCode = 1;//unique code
        int flags =0;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent,flags);

        alarmManager.cancel(pendingIntent);

        ((TextView)findViewById(R.id.txtAlarmInfo)).setText("Alarm Cancelled");
    }

    private void updateTimeText(Calendar c) {
        String text = "Alarm set for : ";
        text += DateFormat.getTimeInstance().format(c.getTime());
        ((TextView)findViewById(R.id.txtAlarmInfo)).setText(text);
    }
}