package edu.uta.eventapp.uta_event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Console extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_console);

        Button verifyEvent;
        Button deleteUser;

        verifyEvent = (Button) findViewById(R.id.bVerifyEvents);
        deleteUser  = (Button) findViewById(R.id.bDeleteUser);

        verifyEvent.setOnClickListener(this);
        deleteUser.setOnClickListener(this);

        }

    public void onClick(View v){

        switch ((v.getId())){
            case R.id.bVerifyEvents:
                startActivity(new Intent(this, AdminVerifyEvent.class));
                break;

            case R.id.bDeleteUser:
                startActivity(new Intent(this, AdminDeleteUser.class));
                break;
        }
    }
}

