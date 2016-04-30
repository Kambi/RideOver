package com.example.rahultheboss.rideover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnLoginClick();
    }

    public void OnLoginClick(){
        Button Login = (Button)findViewById(R.id.login_button);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText username = (EditText)findViewById(R.id.username_text_field);
                String username_string = username.getText().toString();

                EditText password = (EditText)findViewById(R.id.password_text_field);
                String password_string = password.getText().toString();

               String final_password = helper.search_password_string(username_string);

                if(username_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Username Field is Empty! Please Type in Username.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(password_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Password Field is Empty! Please Type in Password.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(final_password.equals(password_string)){
                    Intent login = new Intent("com.example.rahultheboss.rideover.HomeScreenNav");
                    //login.putExtra("Username", username_string);
                    startActivity(login);
                    Toast x = Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT);
                    x.show();
                }
                else{

                    Toast x = Toast.makeText(LoginActivity.this, "Username and Password Don't Match!", Toast.LENGTH_SHORT);
                    x.show();
                }
            }
        });
    }
}

