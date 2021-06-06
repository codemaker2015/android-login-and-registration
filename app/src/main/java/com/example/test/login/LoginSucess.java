package com.example.test.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSucess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucess);
        TextView txtGreeting = findViewById(R.id.txtGreeting);
        String username = getIntent().getStringExtra("username");
        if(!username.equals(""))
            txtGreeting.setText("Welcome " + username);
    }
}
