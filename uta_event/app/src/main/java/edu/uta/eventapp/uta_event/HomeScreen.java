package edu.uta.eventapp.uta_event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener{

    Button viewEvents;
    Button broadcastEvent;
    Button modifyEvent;
    Button settings;
    Button adminConsole;
    Button logout;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        viewEvents = (Button) findViewById(R.id.bViewEvents);
        broadcastEvent = (Button)findViewById(R.id.bBroadcastEvents);
        modifyEvent = (Button)findViewById(R.id.bModifyEvents);
        settings = (Button)findViewById(R.id.bSettings);
        adminConsole = (Button)findViewById(R.id.bAdminConsole);
        logout = (Button)findViewById(R.id.bLogout);
        viewEvents.setOnClickListener(this);
        broadcastEvent.setOnClickListener(this);
        modifyEvent.setOnClickListener(this);
        settings.setOnClickListener(this);
        adminConsole.setOnClickListener(this);
        logout.setOnClickListener(this);
        //session = new Session(HomeScreen.this);
    }
    @Override
    public void onClick(View v) {

        switch ((v.getId())){

            case R.id.bViewEvents:

                startActivity(new Intent(this, ViewEvent.class));

                break;
            case R.id.bBroadcastEvents:

                startActivity(new Intent(this, BroadcastEvent.class));

                break;

            case R.id.bModifyEvents:

                startActivity(new Intent(this, ModifyEvent.class));

                break;

            case R.id.bSettings:

                startActivity(new Intent(this, Settings.class));

                break;

            case R.id.bAdminConsole:

                startActivity(new Intent(this, Admin_Console.class));

                break;

            case R.id.bLogout:

                startActivity(new Intent(this, WelcomeScreen.class));

                break;
        }
    }
}
