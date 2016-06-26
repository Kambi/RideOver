package com.example.rahultheboss.rideover;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ShareARide extends AppCompatActivity implements View.OnClickListener{
    Context context;
    DatabaseHelper helper = new DatabaseHelper(this);
    Button btnDatePicker, btnTimePicker;

    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String sr_name_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_aride);

        OnClickShareTheRide();

        //buttons on side of date and time
        btnDatePicker=(Button)findViewById(R.id.calender_button);
        btnTimePicker=(Button)findViewById(R.id.clock_button);

        //convert to text
        txtDate=(EditText)findViewById(R.id.sr_date_text_field);
        txtTime=(EditText)findViewById(R.id.sr_time_text_field);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }


    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            if(hourOfDay > 0 && hourOfDay < 12) {
                                txtTime.setText(hourOfDay + ":" + minute + " AM");
                                if(minute < 10){
                                    txtTime.setText(hourOfDay + ":" + "0" + minute + " AM");
                                }
                            }
                            else if(hourOfDay == 0){
                                txtTime.setText(hourOfDay+12 + ":" + minute + " AM");
                                if(minute < 10){
                                    txtTime.setText(hourOfDay+12 + ":" + "0" + minute + " AM");
                                }
                            }
                            else if (hourOfDay == 12){
                                txtTime.setText(hourOfDay + ":" + minute + " PM");
                                if(minute < 10){
                                    txtTime.setText(hourOfDay + ":" + "0" + minute + " PM");
                                }
                            }
                            else {
                                txtTime.setText(hourOfDay-12 + ":" + minute + " PM");
                                if(minute < 10){
                                    txtTime.setText(hourOfDay-12 + ":" + "0" + minute + " PM");
                                }
                            }


                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    public void OnClickShareTheRide(){
        //String sr_name_string;
        Button share_button = (Button)findViewById(R.id.share_confirm);
        share_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sr_name = (EditText)findViewById(R.id.sr_name_text_field);
                        sr_name_string = sr_name.getText().toString();

                        EditText sr_leaving_from = (EditText)findViewById(R.id.sr_leaving_from_text_field);
                        String sr_leaving_from_string = sr_leaving_from.getText().toString();

                        EditText sr_going_to = (EditText)findViewById(R.id.sr_going_to_text_field);
                        String sr_going_to_string = sr_going_to.getText().toString();

                        EditText sr_seats = (EditText)findViewById(R.id.sr_seats_text_field);
                        String sr_seats_string = sr_seats.getText().toString();


                        EditText sr_price = (EditText)findViewById(R.id.sr_price_text_field);
                        String sr_price_string = sr_price.getText().toString();



                        EditText sr_phone_number = (EditText)findViewById(R.id.sr_phone_number_field);
                        String sr_phone_number_string = sr_phone_number.getText().toString();


                        String sr_date = txtDate.getText().toString();
                        String sr_time = txtTime.getText().toString();

                        //checks to see if the fields are empty or not.
                        if(sr_name_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Name Field is Empty! Please Type in First Name.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_leaving_from_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Leaving From Field is Empty! Please Type in Leaving From.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        if(sr_going_to_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Going To Field is Empty! Please Type in Going To.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_date.equals("")) {
                            Toast.makeText(getApplicationContext(), "Date Field is Empty! Please Type in Date.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_time.equals("")) {
                            Toast.makeText(getApplicationContext(), "Time Field is Empty! Please Type in Time.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_seats_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Seats Field is Empty! Please Type in Number of Seats.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_price_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Price Field is Empty! Please Type in Price.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(sr_phone_number_string.equals("")) {
                            Toast.makeText(getApplicationContext(), "Phone Number Field is Empty! Please Type in Phone Number.", Toast.LENGTH_LONG).show();
                            return;
                        }



                        //check validity of fields

                        if(helper.checkFirstName(sr_name_string) == false){
                            Toast.makeText(getApplicationContext(), "Name is not what you signed up with! Please enter name you signed up.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            Intent i = new Intent("com.example.rahultheboss.rideover.HomeScreenNav");
                            i.putExtra("Username", sr_name_string);
                            Log.d("Debug: ", "The username put in was " + sr_name_string);
                            startActivity(i);

                            Rides r = new Rides();
                            r.setSr_name(sr_name_string);
                            r.setSr_leaving_from(sr_leaving_from_string);
                            r.setSr_going_to(sr_going_to_string);
                            r.setSr_date(sr_date);
                            r.setSr_time(sr_time);
                            r.setSr_seats(sr_seats_string);
                            r.setSr_price(sr_price_string);
                            r.setSr_phoneNumber(sr_phone_number_string);

                            helper.insertRides(r);

                            Toast.makeText(getApplicationContext(), "Ride Shared!", Toast.LENGTH_LONG).show();
                            return;
                        }


                    }
                }

        );
    }


}
