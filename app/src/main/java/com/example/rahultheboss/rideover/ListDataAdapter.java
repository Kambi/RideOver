package com.example.rahultheboss.rideover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milindsinghal on 4/29/2016.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView NAME, LEAVEFROM, GOTO, DATE, TIME, SEATS, PRICE;
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
            row.setTag(layoutHandler);

        }
        else
        {
            layoutHandler = (LayoutHandler)row.getTag();

        }

        Rides r = (Rides)this.getItem(position);
        layoutHandler.NAME.setText(r.getSr_name());
        layoutHandler.LEAVEFROM.setText(r.getSr_leaving_from());
        layoutHandler.GOTO.setText(r.getSr_going_to());
        layoutHandler.DATE.setText(r.getSr_date());
        layoutHandler.TIME.setText(r.getSr_time());
        layoutHandler.SEATS.setText(r.getSr_seats());
        layoutHandler.PRICE.setText(r.getSr_price());


        return row;
    }
}
