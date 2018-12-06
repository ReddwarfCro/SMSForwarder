package com.example.reddwarf.smsforwarder;

import android.telephony.SmsManager;
import android.util.Log;

public class ProcessMessage {

    public void process(String sender, String message, String myNumber)
    {
        if (myNumber.isEmpty()){
            return;
        }

        try {
            /// if I am the sender then "process" message and forward it further or pay SMS parking in
            /// one of City of Zagreb parking zones
            if (sender.equals(myNumber))
            {
                if (message.toLowerCase().startsWith("p:")) {
                    String[] msg = message.split(":");
                    CarsAndZones cars = new CarsAndZones();

                    message = cars.GetCar(msg[1]).licencePlate + (msg[2].toLowerCase().trim().equals("12") ? "#30" : "");
                    sender = cars.GetZone(msg[2].trim());

                } else if (message.toLowerCase().startsWith("c:")) {
                    String[] msg = message.split(":");

                    message = msg[1];
                    sender = msg[2];

                } else {
                    message = "De sto ti je? Jesi pio? Ne kuzim";
                }

                sendSMS(sender, message);
            } else {
                sendSMS(myNumber, sender + ";" +message);
            }
        }
        catch (Exception e) {
            Log.e("IvicaLog", "Exception SmsProcess: " +e);

        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        try {

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            Log.e("IvicaLog", "Exception smsReceiver" +e);

        }
    }
}
