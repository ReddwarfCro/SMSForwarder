package com.example.reddwarf.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class IncomingSms  extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();
    private SettingsDatabase settings;

    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        settings = new SettingsDatabase(context);
        settings = settings.open();

        if (settings.getSinlgeEntry("active").equals("true")) {
            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        ProcessMessage processor = new ProcessMessage();
                        String number = settings.getSinlgeEntry("myNumber");
                        processor.process(senderNum, message, number);

                    }
                }

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }
        }
    }
}