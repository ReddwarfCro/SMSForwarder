package com.example.reddwarf.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class GetNotification  extends BroadcastReceiver {

    private SettingsDatabase settings;

    public void onReceive(Context context, Intent intent) {

        settings = new SettingsDatabase(context);
        settings = settings.open();

        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {

            String sendLowBattery = settings.getSinlgeEntry("sendLowBatteryNotification");
            if (settings.getSinlgeEntry("active").equals("true") && sendLowBattery.equals("NOT EXIST")) {
                try {
                    settings.insertEntry("sendLowBatteryNotification", "send");
                    ProcessMessage processor = new ProcessMessage();
                    String number = settings.getSinlgeEntry("myNumber");
                    processor.process("System", "Battery LOW", number);
                } catch (Exception e) {
                    Log.e("batteryLow", "Exception smsReceiver" + e);

                }
            }
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Cleared", Toast.LENGTH_LONG).show();
            settings.deleteEntry("sendLowBatteryNotification");
        }
    }
}