package com.deepak.broadcastreceiverexample.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class OrderedBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        String stringExtra = resultExtras.getString("stringFromSenderReceiver");
        if(stringExtra!=null){

            resultCode++;
            String toastText = "OrderedBroadcastReceiver 2 trigger\n" + "resultCode:"
                    + resultCode + "\n" + "resultData:" + resultData + "\n"
                    +"stringFromSenderReceiver: " + stringExtra;

            Toast.makeText(context,toastText ,Toast.LENGTH_LONG).show();

            resultData = "OR2";
            resultExtras.putString("stringFromSenderReceiver",stringExtra + "->OBR2");
            setResult(resultCode,resultData,resultExtras);


            // This does not reach the broadcast receiver after this on. OrderedBroadcastReceiver1 is not called in this case.
            abortBroadcast();
        }
        else {
            Toast.makeText(context, "OrderedBroadcastReceiver 2 triggered : " + intent.getAction(), Toast.LENGTH_LONG).show();

            final Handler handler = new Handler();

            PendingResult pendingResult = goAsync();

            new Thread(() -> {
                SystemClock.sleep(10000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "OBR 2 triggered via gosync thread: " + intent.getAction(), Toast.LENGTH_LONG).show();
                        Log.d("BroadcastReceiver","OBR 2 triggered via gosync thread:"+intent.getAction());
                    }
                });
            }).start();
            //heavy work on a background thread
            pendingResult.finish();


        }
        Log.d("BroadcastReceiver","OBR 2 triggered:"+intent.getAction());
    }
}
