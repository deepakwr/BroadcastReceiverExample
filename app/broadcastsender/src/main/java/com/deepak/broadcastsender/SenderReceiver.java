package com.deepak.broadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SenderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        String stringExtra = resultExtras.getString("stringFromSenderReceiver");
        if(stringExtra!=null) {

            resultCode++;
            String toastText = "SenderReceiver triggered\n" + "resultCode:"
                    + resultCode + "\n" + "resultData:" + resultData + "\n"
                    + "stringFromSenderReceiver: " + stringExtra;

            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();

            resultData = "SR";
            resultExtras.putString("stringFromSenderReceiver", stringExtra + "->SR");
            setResult(resultCode, resultData, resultExtras);
        }
        Log.d("BroadcastReceiver","OBR SenderReceiver triggered:"+intent.getAction());
    }
}
