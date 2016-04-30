package com.example.rahultheboss.rideover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        String new_username = getIntent().getStringExtra("Username");

        TextView text_view = (TextView)findViewById(R.id.username_textview);
        text_view.setText(new_username);

        OnClickGetRide();
        OnClickShareARide();
    }

    public void OnClickGetRide(){
        Button getRide = (Button)(findViewById(R.id.get_ride));
        getRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.rahultheboss.rideover.GetARide");
                startActivity(i);
            }
        });
    }

    public void OnClickShareARide(){
        Button shareRide = (Button)(findViewById(R.id.share_ride));
        shareRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.rahultheboss.rideover.ShareARide");
                startActivity(i);
            }
        });
    }

}
