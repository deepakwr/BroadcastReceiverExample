package com.deepak.broadcastreceiverexample.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OrderedBroadcastReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        String stringExtra = resultExtras.getString("stringFromSenderReceiver");
        if(stringExtra!=null){

            resultCode++;
            String toastText = "OrderedBroadcastReceiver 1 trigger\n" + "resultCode:"
                    + resultCode + "\n" + "resultData:" + resultData + "\n"
                    +"stringFromSenderReceiver: " + stringExtra;

            Toast.makeText(context,toastText ,Toast.LENGTH_LONG).show();

            resultData = "OR1";
            resultExtras.putString("stringFromSenderReceiver",stringExtra + "->OBR1");
            setResult(resultCode,resultData,resultExtras);
        }
        else{
            Toast.makeText(context,"OrderedBroadcastReceiver 1 triggered : " + intent.getAction(), Toast.LENGTH_LONG).show();
        }

        Log.d("BroadcastReceiver","OBR 1 triggered:"+intent.getAction());
    }
}
