package edu.uta.eventapp.uta_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        login = (Button)findViewById(R.id.bLoginFirst);
        register = (Button) findViewById(R.id.bForgotPwdFirst);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bLoginFirst:

                startActivity(new Intent(this, Login.class));

                break;

            case R.id.bForgotPwdFirst:

                startActivity(new Intent(this, Register.class));

                break;
        }
    }
}