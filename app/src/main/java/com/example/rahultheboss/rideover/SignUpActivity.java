package com.example.rahultheboss.rideover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        OnRegisterClick();

    }

    //@Override
    public void OnRegisterClick(){
        Button register = (Button)findViewById(R.id.register_button);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText name = (EditText)findViewById(R.id.name_text_field);
                EditText username = (EditText)findViewById(R.id.username_text_field);
                EditText email = (EditText)findViewById(R.id.email_text_field);
                EditText password = (EditText)findViewById(R.id.password_text_field);


                String name_string = name.getText().toString();
                String username_string = username.getText().toString();
                String email_string = email.getText().toString();
                String password_string = password.getText().toString();


                //Intent login = new Intent("com.example.rahultheboss.rideover.LoginActivity");
                //startActivity(login);

                //checks to see if the fields are empty or not.
                if(name_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Name Field is Empty! Please Type in First Name.", Toast.LENGTH_LONG).show();

                    return;
                }

                if(username_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Username Field is Empty! Please Type in Username.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(email_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Email Field is Empty! Please Type in Email.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password_string.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Password Field is Empty! Please Type in Password.", Toast.LENGTH_LONG).show();
                    return;
                }

                //checks for validity of the fields. Example: password, email, and username fields need to be correct to signup
                if(helper.checkUsername(username_string) == true){
                    Toast.makeText(getApplicationContext(), "This username already exists. Please Enter Different Username", Toast.LENGTH_LONG).show();
                }
                else if(helper.checkEmail(email_string) == true){
                    Toast.makeText(getApplicationContext(), "This email already exists. Please Enter Different Email Address", Toast.LENGTH_LONG).show();
                }
                else if(!(email_string.contains("@") &&
                        ((email_string.contains(".com") || email_string.contains(".net") || email_string.contains(".org") || email_string.contains(".edu"))))){
                    Toast.makeText(getApplicationContext(), "Email Format is Incorrect! Example: ttwinkle10166@yahoo.com or .net or .org or .edu", Toast.LENGTH_LONG).show();
                }
                else if(password_string.length() < 4){
                    Toast.makeText(getApplicationContext(), "Password is too short. Minimum Length of password is 4", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent login = new Intent("com.example.rahultheboss.rideover.LoginActivity");
                    startActivity(login);
                    Toast.makeText(getApplicationContext(), "SignUp Successful.", Toast.LENGTH_LONG).show();

                    Contact c = new Contact();
                    c.setName(name_string);
                    c.setUsername(username_string);
                    c.setEmail(email_string);
                    c.setPassword(password_string);

                    helper.insertContact(c);
                }





            }
        });
    }


}
