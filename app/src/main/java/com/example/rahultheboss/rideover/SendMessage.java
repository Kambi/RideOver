package com.example.rahultheboss.rideover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ttwin on 5/12/2016.
 */
public class SendMessage extends AppCompatActivity {

    Button sendMessageButton;
    TextView PhoneNumber;
    EditText MessageBody;
    String username_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bundle bundle = getIntent()
        setContentView(R.layout.activity_send_message);

        sendMessageButton = (Button) findViewById(R.id.sendSMSBtn);
        PhoneNumber = (TextView) findViewById(R.id.toPhoneNumberET);
        username_string = getIntent().getStringExtra("Username");

        Log.d("Debug: ", "About to send a message as " + username_string);
        MessageBody = (EditText) findViewById(R.id.smsMessageET);

        Bundle extras = getIntent().getExtras();
        final String phonenumber = extras.getString("phone_number");
        PhoneNumber.setText(phonenumber);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS(phonenumber);
            }
        });

    }
    protected void sendSMS(String phoneNumber) {
        String phonenumber = phoneNumber;

        String smsMessage = MessageBody.getText().toString();


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, smsMessage, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();

            //Intent myIntent = new Intent(SendMessage.this, HomeScreenNav.class);
            Intent myIntent = new Intent("com.example.rahultheboss.rideover.HomeScreenNav");
            myIntent.putExtra("Username", username_string);
            //Log.d("Debug: ", "About to start myIntent");
            startActivity(myIntent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }



    }
}


