package com.example.reddwarf.smsforwarder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class SMSForwardActivity extends AppCompatActivity {

    public CheckBox active;
    private SettingsDatabase settings;
    private boolean isActive;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsforward);

        /// SMSForward can be turned of and on
        /// eq, when in roaming

        active = findViewById(R.id.checkBox);
        number = findViewById(R.id.editText);

        settings = new SettingsDatabase(getApplicationContext());

        settings = settings.open();

        String activeValue = settings.getSinlgeEntry("active");
        if (activeValue.equals("NOT EXIST")) {
            settings.insertEntry("active", String.valueOf(isActive));
        }
        else {
            active.setChecked(Boolean.valueOf(activeValue));
        }

        String myNumber = settings.getSinlgeEntry("myNumber");
        if (myNumber.equals("NOT EXIST")) {
            settings.insertEntry("myNumber", "");
        }
        else {
            number.setText(myNumber);
        }
    }

    public void SaveSettings(View view){
        try {
            settings = settings.open();
            isActive = active.isChecked();

            settings.updateEntry("active", String.valueOf(isActive));
            settings.updateEntry("myNumber", number.getText().toString());
        }
        catch (Exception ex)
        {
            Log.e("Error", "error save");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        settings.close();
    }
}


