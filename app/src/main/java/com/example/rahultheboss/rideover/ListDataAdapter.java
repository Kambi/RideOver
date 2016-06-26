package com.example.rahultheboss.rideover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milindsinghal on 4/29/2016.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    String new_user;
    public ListDataAdapter(Context context, int resource, String user_name) {
        super(context, resource);
        new_user = user_name;
    }

    static class LayoutHandler
    {
        TextView NAME, LEAVEFROM, GOTO, DATE, TIME, SEATS, PRICE, PHONENUMBER;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.text_user_name);
            layoutHandler.LEAVEFROM = (TextView)row.findViewById(R.id.text_going_from);
            layoutHandler.GOTO = (TextView)row.findViewById(R.id.text_going_to);
            layoutHandler.DATE = (TextView)row.findViewById(R.id.text_date);
            layoutHandler.TIME = (TextView)row.findViewById(R.id.text_time);
            layoutHandler.SEATS = (TextView)row.findViewById(R.id.text_seats);
            layoutHandler.PRICE = (TextView)row.findViewById(R.id.text_price);
            layoutHandler.PHONENUMBER = (TextView)row.findViewById(R.id.text_phone_number);
            row.setTag(layoutHandler);

        }
        else
        {
            layoutHandler = (LayoutHandler)row.getTag();
            new_user = layoutHandler.NAME.toString();
            Log.d("Debug: ", "In list adapter as " + new_user);

        }



        final Rides r = (Rides)this.getItem(position);

        layoutHandler.NAME.setText(r.getSr_name());
        layoutHandler.LEAVEFROM.setText(r.getSr_leaving_from());
        layoutHandler.GOTO.setText(r.getSr_going_to());
        layoutHandler.DATE.setText(r.getSr_date());
        layoutHandler.TIME.setText(r.getSr_time());
        layoutHandler.SEATS.setText(r.getSr_seats());
        layoutHandler.PRICE.setText(r.getSr_price());
        layoutHandler.PHONENUMBER.setText(r.getSr_phoneNumber());

        Button gmap = (Button) row.findViewById(R.id.ConMap);
        gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("from",r.getSr_leaving_from());
                extras.putString("to",r.getSr_going_to());
                intent.putExtras(extras);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        Button sendMessage = (Button) row.findViewById(R.id.send_message_button);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), SendMessage.class);
                Bundle extras = new Bundle();
                extras.putString("phone_number",r.getSr_phoneNumber());
                i.putExtras(extras);
                i.putExtra("Username", new_user);
                Log.d("Debug: ", "In list data adapter " + new_user);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(i);
            }
        });




        return row;
    }


}
