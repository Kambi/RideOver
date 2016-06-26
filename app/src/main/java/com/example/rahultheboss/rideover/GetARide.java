package com.example.rahultheboss.rideover;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;

public class GetARide extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDb = new DatabaseHelper(this);
    Button btnDatePicker;
    EditText txtDate;
    EditText gr_leaving_from, gr_going_to;
    private int mYear, mMonth, mDay;
    ListView listView;
    ListDataAdapter listDataAdapter;
    String new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_aride);
        OnClickGetARide();
        OnClickViewAllRides();
        btnDatePicker=(Button)findViewById(R.id.calender_button);
        txtDate=(EditText)findViewById(R.id.gr_date_text_field);
        btnDatePicker.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("Username")!= null){
            new_user = bundle.getString("Username");
            Log.d("Debug: ", new_user);
        }


    }

 /*   public void OnClickGetARide(View view)
    {
        Intent intent = new Intent(this,DataListActivity.class);
        startActivity(intent);
    }
 */
   public void OnClickGetARide() {
        Button get_ride_button = (Button) findViewById(R.id.find_rides_confirm);

        get_ride_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Intent i = new Intent(this,DataListActivity.class);
                      //  startActivity(i);

                        gr_leaving_from = (EditText)findViewById(R.id.gr_leaving_from_text_field);
                        String gr_leaving_from_string = gr_leaving_from.getText().toString();

                        gr_going_to = (EditText)findViewById(R.id.gr_going_to_text_field);
                        String gr_going_to_string = gr_going_to.getText().toString();

                        String gr_date = txtDate.getText().toString();

                        setContentView(R.layout.activity_data_list);
                        listView = (ListView)findViewById(R.id.list_view);
                        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout, new_user);
                        Log.d("Debug: ", "In get a ride the user name is " + listDataAdapter.new_user);
                        listView.setAdapter(listDataAdapter);

                        Cursor res = myDb.getAllData(gr_leaving_from_string, gr_going_to_string, gr_date);
                        if(res.getCount() == 0) {

                            //show message
                            showMessage("Sorry", "No rides found");
                            return;
                        }

                        String name, leavingfrom, goingto, date, time, seats, price, phoneNumber;
                        //StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                    /*        //buffer.append(res.getString(0));
                            buffer.append(res.getString(1) + "\n");
                            buffer.append("Leaving from: " + res.getString(2) + "\n");
                            buffer.append("Going to: " + res.getString(3) + "\n");
                            buffer.append("Date: " + res.getString(4) + "\n");
                            buffer.append("Time: " + res.getString(5) + "\n");
                            buffer.append("Seats: " + res.getString(6) + "\n");
                            buffer.append("Price: " + res.getString(7) + "\n\n");

                      */
                           name = res.getString(1);
                            leavingfrom = res.getString(2);
                            goingto = res.getString(3);
                            date = res.getString(4);
                            time = res.getString(5);
                            seats = res.getString(6);
                            price = res.getString(7);
                            phoneNumber = res.getString(8);

                            Rides r = new Rides();
                            r.setSr_name(name);
                            r.setSr_leaving_from(leavingfrom);
                            r.setSr_going_to(goingto);
                            r.setSr_date(date);
                            r.setSr_time(time);
                            r.setSr_seats(seats);
                            r.setSr_price(price);
                            r.setSr_phoneNumber(phoneNumber);
                            listDataAdapter.add(r);

                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {

                                if(position == 1)
                                {

                                    Intent myIntent = new Intent(GetARide.this, HomeScreen.class);
                                    myIntent.putExtra("Username", new_user);
                                    Log.d("Debug: ", "Going from get a ride to home screen");
                                    startActivityForResult(myIntent, 0);
                                }

                            }
                        });


                        //Show data
                       // showMessage("Rides", buffer.toString());


                    }
                }

        );
    }

    public void OnClickViewAllRides() {
        Button all_rides_button = (Button) findViewById(R.id.all_rides_confirm);
        all_rides_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        setContentView(R.layout.activity_data_list);
                        listView = (ListView)findViewById(R.id.list_view);
                        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout, new_user);
                        Log.d("Debug: ", "In get all ride the user name is " + listDataAdapter.new_user);
                        listView.setAdapter(listDataAdapter);

                        Cursor res2 = myDb.getAllTheData();
                        if(res2.getCount() == 0) {

                            //show message
                            showMessage("Sorry", "No rides found");
                            return;
                        }

                        String name, leavingfrom, goingto, date, time, seats, price, phoneNumber;

                       // StringBuffer buffer = new StringBuffer();
                        while(res2.moveToNext()) {
                     /*       //buffer.append(res2.getString(0));
                            buffer.append(res2.getString(1) + "\n");
                            buffer.append("Leaving from: " + res2.getString(2) + "\n");
                            buffer.append("Going to: " + res2.getString(3) + "\n");
                            buffer.append("Date: " + res2.getString(4) + "\n");
                            buffer.append("Time: " + res2.getString(5) + "\n");
                            buffer.append("Seats: " + res2.getString(6) + "\n");
                            buffer.append("Price: " + res2.getString(7) + "\n\n");
                    */
                            name = res2.getString(1);
                            leavingfrom = res2.getString(2);
                            goingto = res2.getString(3);
                            date = res2.getString(4);
                            time = res2.getString(5);
                            seats = res2.getString(6);
                            price = ("$" + res2.getString(7));
                            phoneNumber = res2.getString(8);

                            Rides r = new Rides();
                            r.setSr_name(name);
                            r.setSr_leaving_from(leavingfrom);
                            r.setSr_going_to(goingto);
                            r.setSr_date(date);
                            r.setSr_time(time);
                            r.setSr_seats(seats);
                            r.setSr_price(price);
                            r.setSr_phoneNumber(phoneNumber);
                            listDataAdapter.add(r);
                        }


                        //Show data
                        //showMessage("Rides", buffer.toString());
                    }
                }

        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
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

                            txtDate.setText( (monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }


}
